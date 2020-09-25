package com.example.prohub.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prohub.Helper.UserHelperClass;
import com.example.prohub.MainActivity;
import com.example.prohub.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    ImageView backBtn;
    Button createAccount, login;
    TextView titleText;
    private ProgressDialog loadingBar;

    //get data validation
    TextInputLayout inputName, inputPhoneNumber, inputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        backBtn = findViewById(R.id.signUp_back_btn);
        createAccount = findViewById(R.id.register_btn);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);

        //hooks for data validation
        inputName = findViewById(R.id.register_user_name_input);
        inputPassword = findViewById(R.id.register_password_input);
        inputPhoneNumber = findViewById(R.id.register_phone_number_input);
        loadingBar = new ProgressDialog(this);

    }


    public void callNextSignupScreen(View view) {
        //get all the values

        String name = inputName.getEditText().getText().toString();
        String phone = inputPhoneNumber.getEditText().getText().toString();
        String password = inputPassword.getEditText().getText().toString();

        if (!validateFullName() | !validatePhone() | !validatePassword()){
            return;
        }
        else{
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatephoneNumber(name, phone, password);
        }

    }

    private void ValidatephoneNumber(final String name, final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    //hashmap for put data in firebase databse
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("password", password);
                    userdataMap.put("name", name);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(SignUp.this,"Congrats, your account has been created. ", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(SignUp.this,Login.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(SignUp.this,"Network error, please try after some time.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(SignUp.this,"This " + phone + "already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                    Toast.makeText(SignUp.this,"Please try agian using another phone number.",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUp.this, StartUpScreen.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void gotoStartUpScreen(View view) {
        startActivity(new Intent(getApplicationContext(), StartUpScreen.class));
    }

    public void gotoLoginScreen(View view){
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    private boolean validateFullName() {
        String val = inputName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            inputName.setError("Full Name can not be empty");
            return false;
        } else {
            inputName.setError(null);
            inputName.setErrorEnabled(false);
            return true;
        }
    }


    private boolean validatePhone(){
        String val = inputPhoneNumber.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            inputPhoneNumber.setError("Phone Number can not be empty");
            return false;
        } else {
            inputPhoneNumber.setError(null);
            inputPhoneNumber.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = inputPassword.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                //"(?=.*[0-9])"+        //at least 1 digit
                //"(?=.*[a-z])"+        //at least 1 lower case letter
                //"(?=.*[A-Z])"+        //at least 1 upper case letter
                "(?=.*[a-zA-Z])"+       //any letter
                "(?=.*[@#$%^&+=])"+     //at least 1 special character
                "(?=\\S+$)"+            //no white spaces
                ".{4,}"+                //at least 4 characters
                "$";

        if (val.isEmpty()) {
            inputPassword.setError("Password can not be empty");
            return false;
        } else if (!val.matches(checkPassword)){
            inputPassword.setError("Password should contain 4 characters and a special character");
            return false;
        }
        else {
            inputPassword.setError(null);
            inputPassword.setErrorEnabled(false);
            return true;
        }
    }


}
