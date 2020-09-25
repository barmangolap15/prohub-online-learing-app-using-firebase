package com.example.prohub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.prohub.model.CModel;
import com.example.prohub.model.JavaModel;
import com.example.prohub.viewadapter.CViewHolder;
import com.example.prohub.viewadapter.JavaViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CActivity extends AppCompatActivity {

    private DatabaseReference CRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar jProgress;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_c);

        backBtn = (ImageView)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });


        jProgress = (ProgressBar)findViewById(R.id.progressBar);
        jProgress.setVisibility(View.VISIBLE);


        CRef  = FirebaseDatabase.getInstance().getReference().child("Cprog");
        recyclerView = findViewById(R.id.c_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<CModel> options = new FirebaseRecyclerOptions.Builder<CModel>()
                .setQuery(CRef, CModel.class)
                .build();
        FirebaseRecyclerAdapter<CModel, CViewHolder> adapter = new FirebaseRecyclerAdapter<CModel, CViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CViewHolder holder, int position, @NonNull final CModel model) {
                jProgress.setVisibility(View.INVISIBLE);
                holder.c_name.setText(model.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CActivity.this,Main2Activity.class);
                        intent.putExtra("cid",model.getCid());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public CViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.c_item_layout,parent,false);
                CViewHolder holder = new CViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
