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

import com.example.prohub.HomeActivity;
import com.example.prohub.MachineMainActivity;
import com.example.prohub.MainActivity;
import com.example.prohub.R;
import com.example.prohub.model.CnModel;
import com.example.prohub.model.MlModel;
import com.example.prohub.viewadapter.CnViewHolder;
import com.example.prohub.viewadapter.MlViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MachineLearning extends AppCompatActivity {

    private DatabaseReference MlRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar jProgress;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_machine_learning);

        backBtn = (ImageView)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MachineLearning.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        jProgress = (ProgressBar)findViewById(R.id.progressBar);
        jProgress.setVisibility(View.VISIBLE);


        MlRef  = FirebaseDatabase.getInstance().getReference().child("ML");
        recyclerView = findViewById(R.id.ml_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<MlModel> options = new FirebaseRecyclerOptions.Builder<MlModel>()
                .setQuery(MlRef, MlModel.class)
                .build();
        FirebaseRecyclerAdapter<MlModel, MlViewHolder> adapter = new FirebaseRecyclerAdapter<MlModel, MlViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MlViewHolder holder, int position, @NonNull final MlModel model) {
                jProgress.setVisibility(View.INVISIBLE);
                holder.ml_name.setText(model.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MachineLearning.this, MachineMainActivity.class);
                        intent.putExtra("mid",model.getMid());
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @NonNull
            @Override
            public MlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ml_item_layout,parent,false);
                MlViewHolder holder = new MlViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
