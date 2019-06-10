package com.heaven7.android.hook.app;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.heaven7.android.hook.Hooks;
import com.heaven7.android.hook.item.StartActivityItem;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        Hooks.ofStartActivity(base, new StartActivityItem(
                new ComponentName(this, MainActivity3.class), new Intent(base, MainActivity2.class)
                ));
    }
}
