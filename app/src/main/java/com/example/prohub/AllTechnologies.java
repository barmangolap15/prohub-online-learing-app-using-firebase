package com.example.prohub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.prohub.categories.Android;
import com.example.prohub.categories.BigData;
import com.example.prohub.categories.ComputerNetwork;
import com.example.prohub.categories.MachineLearning;

public class AllTechnologies extends AppCompatActivity implements View.OnClickListener {

    ImageView backPressedBtn;
    Button androidBtn, MLBtn, CNBtn, BigDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_technologies);

        backPressedBtn = findViewById(R.id.back_pressed);
        backPressedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllTechnologies.super.onBackPressed();
            }
        });

        androidBtn = findViewById(R.id.buttonAndroid);
        MLBtn = findViewById(R.id.buttonMl);
        CNBtn = findViewById(R.id.buttonCn);
        BigDataBtn = findViewById(R.id.buttonBigData);

        androidBtn.setOnClickListener(this);
        MLBtn.setOnClickListener(this);
        CNBtn.setOnClickListener(this);
        BigDataBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAndroid:
                startActivity(new Intent(getApplicationContext(), Android.class));
                break;
            case R.id.buttonMl:
                startActivity(new Intent(getApplicationContext(), MachineLearning.class));
                break;
            case R.id.buttonCn:
                startActivity(new Intent(getApplicationContext(), ComputerNetwork.class));
                break;
            case R.id.buttonBigData:
                startActivity(new Intent(getApplicationContext(), BigData.class));
                break;
        }
    }
}
