package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogin extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText, loginUsernameEditText, loginPasswordEditText;
    private Button signUpButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginUsernameEditText = findViewById(R.id.loginUsernameEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);

        ParseUser.getCurrentUser().logOut();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Sign Up On click listener /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser parseUser = new ParseUser();
                parseUser.setUsername(usernameEditText.getText().toString());
                parseUser.setPassword(passwordEditText.getText().toString());

                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {


                        if (e == null){

                            FancyToast.makeText(SignUpLogin.this,"Signed in Successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        }
                        else{

                            FancyToast.makeText(SignUpLogin.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        }
                    }
                });
            }
        });


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////        Login On clicklistener    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(loginUsernameEditText.getText().toString(), loginPasswordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null){

                            FancyToast.makeText(SignUpLogin.this, "Successfully Login", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                            Intent intent = new Intent(SignUpLogin.this,Welcome.class);
                            startActivity(intent);

                        }else{

                            FancyToast.makeText(SignUpLogin.this, "Try again, invalid username & password", FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        }

                    }
                });
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    }
}
