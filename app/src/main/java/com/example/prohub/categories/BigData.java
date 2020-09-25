package com.example.prohub.categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.prohub.BigDataMainActivity;
import com.example.prohub.HomeActivity;
import com.example.prohub.R;
import com.example.prohub.model.BigDataModel;
import com.example.prohub.viewadapter.BigDataViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BigData extends AppCompatActivity {

    private DatabaseReference BigDataRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar jProgress;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bigdata);

        backBtn = (ImageView)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BigData.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        jProgress = (ProgressBar)findViewById(R.id.progressBar);
        jProgress.setVisibility(View.VISIBLE);


        BigDataRef  = FirebaseDatabase.getInstance().getReference().child("BigData");
        recyclerView = findViewById(R.id.bigData_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<BigDataModel> options = new FirebaseRecyclerOptions.Builder<BigDataModel>()
                .setQuery(BigDataRef, BigDataModel.class)
                .build();
        FirebaseRecyclerAdapter<BigDataModel, BigDataViewHolder> adapter = new FirebaseRecyclerAdapter<BigDataModel, BigDataViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BigDataViewHolder holder, int position, @NonNull final BigDataModel model) {
                jProgress.setVisibility(View.INVISIBLE);
                holder.bigdata_name.setText(model.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(BigData.this, BigDataMainActivity.class);
                        intent.putExtra("bdid",model.getBdid());
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @NonNull
            @Override
            public BigDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bigdata_item_layout,parent,false);
                BigDataViewHolder holder = new BigDataViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


}
