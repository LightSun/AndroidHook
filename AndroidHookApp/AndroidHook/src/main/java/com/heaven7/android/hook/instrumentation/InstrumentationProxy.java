package com.heaven7.android.hook.instrumentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import com.heaven7.android.hook.HookCons;
import com.heaven7.android.hook.utils.HookUtils;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class InstrumentationProxy extends Instrumentation{

    private Instrumentation mInstrumentation;
   // private PackageManager mPackageManager;

    public InstrumentationProxy(Context context, Instrumentation instrumentation) {
        this.mInstrumentation = instrumentation;
        HookUtils.copyFieldValue(instrumentation, this);
        System.out.println(instrumentation);
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        int type = intent.getIntExtra(HookCons.KEY_TYPE, 0);

        if (type == HookCons.TYPE_REPLACE_ACTIVITY) {
            Intent src = intent.getParcelableExtra(HookCons.KEY_SRC_INTENT);
            return mInstrumentation.newActivity(cl, src.getComponent().getClassName(), intent);
        }
        return mInstrumentation.newActivity(cl, className, intent);
    }
}
