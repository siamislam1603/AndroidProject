package com.example.mysdapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DrawActivity extends AppCompatActivity {
    String userGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        PaintView paintView = new PaintView(this);
        setContentView(paintView);
    }
}
