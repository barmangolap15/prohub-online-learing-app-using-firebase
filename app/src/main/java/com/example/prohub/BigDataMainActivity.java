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

import com.example.prohub.categories.BigData;
import com.example.prohub.model.BigDataModel;
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

public class BigDataMainActivity extends AppCompatActivity {

    private TextView text;
    private PDFView pdfView;
    private String bigdataID = "";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_data_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BigDataMainActivity.this, BigData.class);
                startActivity(i);
            }
        });

        bigdataID = getIntent().getStringExtra("bdid");
        pdfView = (PDFView)findViewById(R.id.pdfView);
        text = (TextView) findViewById(R.id.text1);

        getBigDataDetails(bigdataID);
    }

    private void getBigDataDetails(String bigdataID) {
        DatabaseReference cRef = FirebaseDatabase.getInstance().getReference().child("BigData");
        cRef.child(bigdataID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    BigDataModel bigDataModel = dataSnapshot.getValue(BigDataModel.class);
                    text.setText(bigDataModel.getText());
                    Toast.makeText(BigDataMainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
//                    j url = text.getText().toString();
                    new RetrivePdfStream().execute(bigDataModel.getUrl());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BigDataMainActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();

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
