package com.example.prohub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllButtonActivity extends AppCompatActivity {

    Button javBtn, cbtn, cPlusBtn, pyBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_button);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tutorials");
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllButtonActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });

        javBtn = (Button)findViewById(R.id.javBtn);
        cbtn = (Button)findViewById(R.id.cBtn);
        cPlusBtn = (Button)findViewById(R.id.cPlusBtn);
        pyBtn = (Button)findViewById(R.id.pythonBtn);

        javBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllButtonActivity.this,JavaActivity.class);
                startActivity(intent);
            }
        });
        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllButtonActivity.this,CActivity.class);
                startActivity(intent);
            }
        });
        cPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllButtonActivity.this,CplusActivity.class);
                startActivity(intent);
            }
        });
        pyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllButtonActivity.this,PythonActivity.class);
                startActivity(intent);
            }
        });
    }
}
