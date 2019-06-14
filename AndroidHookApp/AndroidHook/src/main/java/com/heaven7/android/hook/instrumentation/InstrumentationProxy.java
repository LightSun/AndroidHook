package com.heaven7.android.hook.instrumentation;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import com.heaven7.android.hook.HookCons;
import com.heaven7.android.hook.utils.HookUtils;
import com.heaven7.java.base.util.ReflectUtils;

import java.lang.reflect.Field;

/**
 * Created by heaven7 on 2019/6/10.
 */
//TODO wait fix all methods for Instrumentation. (static proxy)
public class InstrumentationProxy extends Instrumentation{

    private Instrumentation mInstrumentation;
   // private PackageManager mPackageManager;

    public InstrumentationProxy(Context context, Instrumentation instrumentation) {
        this.mInstrumentation = instrumentation;
        //HookUtils.copyFieldValue(instrumentation, this);
    }

    /**
     * set activity thread for 'newActivity' and etc.
     * @param activityThread the activity thread object
     */
    /*public*/ void setActivityThread(Object activityThread) {
        try {
            Field[] fields = Instrumentation.class.getDeclaredFields();
            //android 在jni 层做了处理。拿不到field.
            Field mThread = getClass().getSuperclass().getDeclaredField("mThread");
            mThread.setAccessible(true);
            if(mThread.get(this) == null){
                mThread.set(this, activityThread);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {
        int type = intent.getIntExtra(HookCons.KEY_TYPE, 0);

        if (type == HookCons.TYPE_REPLACE_ACTIVITY) {
            Intent src = intent.getParcelableExtra(HookCons.KEY_SRC_INTENT);
            return super.newActivity(cl, src.getComponent().getClassName(), intent);
        }
        return super.newActivity(cl, className, intent);
    }
}
