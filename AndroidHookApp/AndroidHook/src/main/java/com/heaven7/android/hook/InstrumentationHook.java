package com.heaven7.android.hook;

import android.content.Context;

import com.heaven7.java.base.util.ReflectUtils;

import java.lang.reflect.InvocationHandler;

/**
 * Created by heaven7 on 2019/6/10.
 */
public final class InstrumentationHook {

    public static void hookInstrumentation(Context context, Callback callback) throws Exception {
        //Class<?> contextImplClass = Class.forName("android.app.ContextImpl");
        //Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = ReflectUtils.getVirtualFieldValue(context, "mMainThread");
        Object instrumentation = ReflectUtils.getVirtualFieldValue(activityThread, "mInstrumentation");
        ReflectUtils.setVirtualFieldValue("mInstrumentation",
                callback.createInstrumentationProxy(instrumentation),
                activityThread);
    }

    public interface Callback {
        InvocationHandler createInstrumentationProxy(Object src);
    }
}
