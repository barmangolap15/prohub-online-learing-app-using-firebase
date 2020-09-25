package com.example.prohub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.prohub.LoginSignup.StartUpScreen;

public class SplashActivity extends AppCompatActivity {

    private  static int SPLASH_TIME_OUT = 5000;
    Animation animation;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        animation = AnimationUtils.loadAnimation(this,R.anim.my_anim);
        logo = (ImageView)findViewById(R.id.logo);
        logo.setAnimation(animation);

        //splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, StartUpScreen.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
