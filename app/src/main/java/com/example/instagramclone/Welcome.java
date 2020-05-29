package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Welcome extends AppCompatActivity {

    private TextView welcomeTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        logoutButton = findViewById(R.id.logoutButton);

        welcomeTextView.setText("Welcome, " + ParseUser.getCurrentUser().get("username"));

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null){

                            FancyToast.makeText(Welcome.this,"Log out successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                            finish();
                        }
                        else{
                            FancyToast.makeText(Welcome.this,"Can't log out",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        }
                    }
                });
            }
        });

    }
}
