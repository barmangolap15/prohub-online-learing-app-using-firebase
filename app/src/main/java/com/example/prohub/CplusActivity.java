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

import com.example.prohub.model.CModel;
import com.example.prohub.model.CplusModel;
import com.example.prohub.viewadapter.CViewHolder;
import com.example.prohub.viewadapter.CplusViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CplusActivity extends AppCompatActivity {

    private DatabaseReference CpRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar jProgress;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cplus);

        backBtn = (ImageView)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CplusActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        jProgress = (ProgressBar)findViewById(R.id.progressBar);
        jProgress.setVisibility(View.VISIBLE);


        CpRef  = FirebaseDatabase.getInstance().getReference().child("Cpluse");
        recyclerView = findViewById(R.id.cp_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<CplusModel> options = new FirebaseRecyclerOptions.Builder<CplusModel>()
                .setQuery(CpRef, CplusModel.class)
                .build();
        FirebaseRecyclerAdapter<CplusModel, CplusViewHolder> adapter = new FirebaseRecyclerAdapter<CplusModel, CplusViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CplusViewHolder holder, int position, @NonNull final CplusModel model) {
                jProgress.setVisibility(View.INVISIBLE);
                holder.cp_name.setText(model.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CplusActivity.this,Main3Activity.class);
                        intent.putExtra("did",model.getDid());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public CplusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cplus_item_layout,parent,false);
                CplusViewHolder holder = new CplusViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
