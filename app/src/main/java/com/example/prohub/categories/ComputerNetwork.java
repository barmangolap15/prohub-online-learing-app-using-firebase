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

import com.example.prohub.ComputerMainActivity;
import com.example.prohub.HomeActivity;
import com.example.prohub.MainActivity;
import com.example.prohub.R;
import com.example.prohub.model.BigDataModel;
import com.example.prohub.model.CnModel;
import com.example.prohub.viewadapter.BigDataViewHolder;
import com.example.prohub.viewadapter.CnViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComputerNetwork extends AppCompatActivity {

    private DatabaseReference CnRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar jProgress;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_computer_network);

        backBtn = (ImageView)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComputerNetwork.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        jProgress = (ProgressBar)findViewById(R.id.progressBar);
        jProgress.setVisibility(View.VISIBLE);


        CnRef  = FirebaseDatabase.getInstance().getReference().child("CN");
        recyclerView = findViewById(R.id.cn_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<CnModel> options = new FirebaseRecyclerOptions.Builder<CnModel>()
                .setQuery(CnRef, CnModel.class)
                .build();
        FirebaseRecyclerAdapter<CnModel, CnViewHolder> adapter = new FirebaseRecyclerAdapter<CnModel, CnViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CnViewHolder holder, int position, @NonNull final CnModel model) {
                jProgress.setVisibility(View.INVISIBLE);
                holder.cn_name.setText(model.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ComputerNetwork.this, ComputerMainActivity.class);
                        intent.putExtra("cnid",model.getCnid());
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @NonNull
            @Override
            public CnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cn_item_layout,parent,false);
                CnViewHolder holder = new CnViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}
