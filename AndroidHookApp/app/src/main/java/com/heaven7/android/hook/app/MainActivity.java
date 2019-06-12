package com.heaven7.android.hook.app;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.heaven7.android.hook.utils.HookUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.tv1);
        //tv.setText(stringFromJNI());
        HookUtils.printMethods(Instrumentation.class);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public void startActivity3(View view) {
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("MainActivity", "_123_");
        startActivity(intent);
    }

    public void startActivity4(View view) {
        Intent intent = new Intent(this, MainActivity4.class);
        intent.putExtra("MainActivity", "_1234_");
        startActivity(intent);
    }
}
