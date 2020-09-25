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

import com.example.prohub.AndroidMainActivity;
import com.example.prohub.HomeActivity;
import com.example.prohub.JavaActivity;
import com.example.prohub.MainActivity;
import com.example.prohub.R;
import com.example.prohub.model.AndroidModel;
import com.example.prohub.model.JavaModel;
import com.example.prohub.viewadapter.AndroidViewHolder;
import com.example.prohub.viewadapter.JavaViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Android extends AppCompatActivity {

    private DatabaseReference AndroidRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar jProgress;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_android);

        backBtn = (ImageView)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Android.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        jProgress = (ProgressBar)findViewById(R.id.progressBar);
        jProgress.setVisibility(View.VISIBLE);


        AndroidRef  = FirebaseDatabase.getInstance().getReference().child("Android");
        recyclerView = findViewById(R.id.android_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<AndroidModel> options = new FirebaseRecyclerOptions.Builder<AndroidModel>()
                .setQuery(AndroidRef, AndroidModel.class)
                .build();
        FirebaseRecyclerAdapter<AndroidModel, AndroidViewHolder> adapter = new FirebaseRecyclerAdapter<AndroidModel, AndroidViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AndroidViewHolder holder, int position, @NonNull final AndroidModel model) {
                jProgress.setVisibility(View.INVISIBLE);
                holder.android_name.setText(model.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Android.this, AndroidMainActivity.class);
                        intent.putExtra("aid",model.getAid());
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @NonNull
            @Override
            public AndroidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.android_item_layout,parent,false);
                AndroidViewHolder holder = new AndroidViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}
