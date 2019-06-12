package com.heaven7.android.hook.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_4);
        System.out.println("extra: " + getIntent().getStringExtra("MainActivity"));
    }
}
