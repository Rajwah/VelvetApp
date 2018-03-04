package com.example.ralha.velvetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


public class HomePage extends AppCompatActivity {
    private ImageView Account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void onClick(ImageView imgView){
        if (imgView == Account){
            startActivity(new Intent(getApplicationContext(), AccountActivity.class));
        }
    }
}

