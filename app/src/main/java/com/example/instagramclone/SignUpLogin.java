package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLogin extends AppCompatActivity {


    private EditText loginEmailEditText, loginPasswordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);
        loginEmailEditText = findViewById(R.id.loginEmailEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);
        loginButton = findViewById(R.id.loginButton);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorAccent));


        if(ParseUser.getCurrentUser() != null){

            ParseUser.logOutInBackground();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser parseUser = new ParseUser();
                parseUser.logInInBackground(loginEmailEditText.getText().toString(), loginPasswordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user != null && e == null){

                            FancyToast.makeText(SignUpLogin.this,user.getUsername() + ", login successfully", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                            Intent intent = new Intent(SignUpLogin.this,Welcome.class);
                            startActivity(intent);
                        }
                        else{
                            FancyToast.makeText(SignUpLogin.this, e.getMessage(), FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        }
                    }
                });
            }
        });

    }
}
