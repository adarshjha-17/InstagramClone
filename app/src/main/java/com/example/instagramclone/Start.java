package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Start extends AppCompatActivity {

    private Button btnCreateNewAccount;
    private TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnCreateNewAccount = findViewById(R.id.btnCreateNewAccount);
        loginTextView = findViewById(R.id.loginTextView);

//        changing statusbar color

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusbarColor));

//        Create new account on click listerner

        btnCreateNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this,SignUp.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

//        Login on click listerner

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Start.this,SignUpLogin.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });



    }
}
