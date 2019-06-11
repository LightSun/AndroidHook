package com.heaven7.android.hook.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_3);
        System.out.println("extra: " + getIntent().getStringExtra("MainActivity"));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        System.out.println("MainActivity3: " + hasFocus);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        System.out.println("onUserLeaveHint");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        System.out.println("onDetachedFromWindow");
    }
}
