package com.example.prohub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prohub.model.FeaturedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class BaseActivity extends AppCompatActivity {

    private TextView mainTitle,mainDesc,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16;
    private ImageView mainImage;
    private CircleImageView courseIcon;
    private Button mainBtn;
    private String baseID = "";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Featured Course");
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });

        baseID = getIntent().getStringExtra("bid");

        mainTitle = (TextView) findViewById(R.id.main_title);
        c1 = (TextView) findViewById(R.id.c1);
        c2 = (TextView) findViewById(R.id.c2);
        c3 = (TextView) findViewById(R.id.c3);
        c4 = (TextView) findViewById(R.id.c4);
        c5 = (TextView) findViewById(R.id.c5);
        c6 = (TextView) findViewById(R.id.c6);
        c7 = (TextView) findViewById(R.id.c7);
        c8 = (TextView) findViewById(R.id.c8);
        c9 = (TextView) findViewById(R.id.c9);
        c10 = (TextView) findViewById(R.id.c10);
        c11 = (TextView) findViewById(R.id.c11);
        c12 = (TextView) findViewById(R.id.c12);
        c13 = (TextView) findViewById(R.id.c13);
        c14 = (TextView) findViewById(R.id.c14);
        c15 = (TextView) findViewById(R.id.c15);
        c16 = (TextView) findViewById(R.id.c16);
        mainDesc = (TextView) findViewById(R.id.main_desc);
        mainImage = (ImageView) findViewById(R.id.main_image);
        courseIcon = (CircleImageView)findViewById(R.id.course_icon);
        mainBtn = (Button)findViewById(R.id.main_btn);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this, AllButtonActivity.class);
                startActivity(intent);
            }
        });

        getFeaturedDetails(baseID);

    }

    private void getFeaturedDetails(String baseID) {
        DatabaseReference featuredRef = FirebaseDatabase.getInstance().getReference().child("Base");
        featuredRef.child(baseID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    FeaturedModel featuredModel = dataSnapshot.getValue(FeaturedModel.class);
                    mainTitle.setText(featuredModel.getTitle());
                    mainDesc.setText(featuredModel.getDescription());
                    c1.setText(featuredModel.getC1());
                    c2.setText(featuredModel.getC2());
                    c3.setText(featuredModel.getC3());
                    c4.setText(featuredModel.getC4());
                    c5.setText(featuredModel.getC5());
                    c6.setText(featuredModel.getC6());
                    c7.setText(featuredModel.getC7());
                    c8.setText(featuredModel.getC8());
                    c9.setText(featuredModel.getC9());
                    c10.setText(featuredModel.getC10());
                    c11.setText(featuredModel.getC11());
                    c12.setText(featuredModel.getC12());
                    c13.setText(featuredModel.getC13());
                    c14.setText(featuredModel.getC14());
                    c15.setText(featuredModel.getC15());
                    c16.setText(featuredModel.getC16());
                    Picasso.get().load(featuredModel.getImage()).into(mainImage);
                    Picasso.get().load(featuredModel.getIcon()).into(courseIcon);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
