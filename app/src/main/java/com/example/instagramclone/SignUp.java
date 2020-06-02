package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity {

    private EditText emailEditText, usernameEditText, passwordEditText;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        signupButton = findViewById(R.id.signupButton);
        emailEditText = findViewById(R.id.emailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser parseUser = new ParseUser();
                parseUser.setUsername(usernameEditText.getText().toString());
                parseUser.setEmail(emailEditText.getText().toString());
                parseUser.setPassword(passwordEditText.getText().toString());
                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null){

                            FancyToast.makeText(SignUp.this,usernameEditText.getText().toString() +", Registered successfully",
                                    FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        }
                        else{
                            FancyToast.makeText(SignUp.this,"Invalid registration",
                                    FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        }
                    }
                });
            }
        });






    }
}
