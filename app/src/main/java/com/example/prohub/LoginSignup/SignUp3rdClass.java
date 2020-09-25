package com.example.prohub.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.prohub.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUp3rdClass extends AppCompatActivity {

    ImageView backBtn;
    Button next, login;
    TextView titleText;

    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up3rd_class);

        backBtn  = findViewById(R.id.signUp_back_btn);
        next  = findViewById(R.id.signup_next_btn);
        login  = findViewById(R.id.signup_login_button);
        titleText  = findViewById(R.id.signup_title_text);

        scrollView = findViewById(R.id.singup_3rd_signup_scroll_view);
        phoneNumber = findViewById(R.id.signup_phone_number);
        countryCodePicker = findViewById(R.id.country_code_picker);

    }

    public void callVerifyOTP(View view){

        if (!validatePhone()){
            return;
        }

        String fullname = getIntent().getStringExtra("fullName");
        String userName = getIntent().getStringExtra("username");
        String userEmail = getIntent().getStringExtra("email");
        String userPassword = getIntent().getStringExtra("password");
        String _date = getIntent().getStringExtra("date");
        String _gender = getIntent().getStringExtra("gender");

        String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _phoneNo = "+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber;


        Intent intent = new Intent(getApplicationContext(),VerifyOTP.class);

        intent.putExtra("fullName",fullname);
        intent.putExtra("username",userName);
        intent.putExtra("email",userEmail);
        intent.putExtra("password",userPassword);
        intent.putExtra("date",_date);
        intent.putExtra("gender",_gender);
        intent.putExtra("phoneNo",_phoneNo);



        startActivity(intent);
    }

    public void gotoSecondSignUpClass(View view){
        Intent intent = new Intent(getApplicationContext(),SingUp2ndClass.class);
        //add transition

        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1] = new Pair<View,String>(next,"transition_next_arrow_btn");
        pairs[2] = new Pair<View,String>(login,"transition_login_btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this,pairs);
        startActivity(intent,options.toBundle());
    }

    private boolean validatePhone(){
        String val = phoneNumber.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            phoneNumber.setError("Phone Number can not be empty");
            return false;
        } else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }
}
