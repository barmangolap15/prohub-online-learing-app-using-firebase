package com.example.prohub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.TextView;

import com.example.prohub.model.JavaModel;
import com.example.prohub.viewadapter.JavaViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JavaActivity extends AppCompatActivity {

    private DatabaseReference JavaRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar jProgress;
    private ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_java);

        backBtn = (ImageView)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JavaActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        jProgress = (ProgressBar)findViewById(R.id.progressBar);
        jProgress.setVisibility(View.VISIBLE);


        JavaRef  = FirebaseDatabase.getInstance().getReference().child("Java");
        recyclerView = findViewById(R.id.java_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<JavaModel> options = new FirebaseRecyclerOptions.Builder<JavaModel>()
                .setQuery(JavaRef, JavaModel.class)
                .build();
        FirebaseRecyclerAdapter<JavaModel, JavaViewHolder> adapter = new FirebaseRecyclerAdapter<JavaModel, JavaViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull JavaViewHolder holder, int position, @NonNull final JavaModel model) {
                jProgress.setVisibility(View.INVISIBLE);
                holder.j_name.setText(model.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(JavaActivity.this,MainActivity.class);
                        intent.putExtra("jid",model.getJid());
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @NonNull
            @Override
            public JavaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.java_item_layout,parent,false);
                JavaViewHolder holder = new JavaViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
