package com.example.prohub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prohub.categories.Android;
import com.example.prohub.categories.MachineLearning;
import com.example.prohub.model.AndroidModel;
import com.example.prohub.model.MlModel;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MachineMainActivity extends AppCompatActivity {

    private TextView text;
    private PDFView pdfView;
    private String mlID = "";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MachineMainActivity.this, MachineLearning.class);
                startActivity(i);
            }
        });

        mlID = getIntent().getStringExtra("mid");
        pdfView = (PDFView)findViewById(R.id.pdfView);
        text = (TextView) findViewById(R.id.text1);

        getMachineDetails(mlID);
    }

    private void getMachineDetails(String mlID) {
        DatabaseReference cRef = FirebaseDatabase.getInstance().getReference().child("ML");
        cRef.child(mlID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    MlModel mlModel = dataSnapshot.getValue(MlModel.class);
                    text.setText(mlModel.getText());
                    Toast.makeText(MachineMainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
//                    j url = text.getText().toString();
                    new RetrivePdfStream().execute(mlModel.getUrl());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MachineMainActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private class RetrivePdfStream extends AsyncTask<String,Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e){
                return  null;
            }
            return  inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }
}
