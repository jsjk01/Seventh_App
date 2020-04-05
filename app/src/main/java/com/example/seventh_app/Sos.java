package com.example.seventh_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Sos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        ImageView sos_img = findViewById(R.id.sos_img);
        Glide.with(this).load(R.drawable.sos).into(sos_img);
    }
}
