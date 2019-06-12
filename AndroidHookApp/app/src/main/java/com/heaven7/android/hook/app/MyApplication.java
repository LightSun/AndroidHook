package com.heaven7.android.hook.app;

import android.app.Application;
import android.content.Context;

import com.heaven7.android.hook.Hooks;
import com.heaven7.android.hook.item.HookPair;
import com.heaven7.java.base.util.ArrayUtils;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        Hooks.ofStartActivity(this,
                new HookPair("com.heaven7.android.hook.app", MainActivity2.class.getName()),
                ArrayUtils.toArray(new HookPair(MainActivity3.class, MainActivity2.class))
                );
    }
}
