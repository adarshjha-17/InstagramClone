package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity {

    private EditText emailEditText, usernameEditText, passwordEditText;
    private Button signupButton;
    private ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        signupButton = findViewById(R.id.signupButton);
        emailEditText = findViewById(R.id.emailEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rootLayout = findViewById(R.id.rootLayout);



        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusbarColor));

        if(ParseUser.getCurrentUser() != null){

            ParseUser.logOutInBackground();
        }

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    signupButton.callOnClick();
                }
                return false;
            }
        });


        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                }
                catch (Exception e){

                    e.printStackTrace();
                }


            }
        });








/////////////////////////////////////// After clicker sign up button /////////////////////////////////////////////////////////////////////////////////////////////////////

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(usernameEditText.getText().toString().equals("") || emailEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")){

                    FancyToast.makeText(SignUp.this,"Email, Username and Password required",FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show();
                }
                else{

                    ParseUser parseUser = new ParseUser();
                    parseUser.setUsername(usernameEditText.getText().toString());
                    parseUser.setEmail(emailEditText.getText().toString());
                    parseUser.setPassword(passwordEditText.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                    progressDialog.setMessage("Signing Up");
                    progressDialog.show();
                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {

                            if(e == null){

                                FancyToast.makeText(SignUp.this,usernameEditText.getText().toString() +", Registered successfully",
                                        FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                                Intent intent = new Intent(SignUp.this,SocialMedia.class);
                                startActivity(intent);
                            }
                            else{
                                FancyToast.makeText(SignUp.this,"Please enter valid registration",
                                        FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }


            }
        });









    }
}
