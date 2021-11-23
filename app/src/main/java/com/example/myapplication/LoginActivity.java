package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.example.myapplication.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bind = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        bind.one.animate().alpha(1.0f).setDuration(5000);
        bind.log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}