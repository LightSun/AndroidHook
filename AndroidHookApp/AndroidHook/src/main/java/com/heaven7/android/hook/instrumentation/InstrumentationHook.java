package com.heaven7.android.hook.instrumentation;

import android.app.Instrumentation;
import android.content.Context;

import com.heaven7.android.hook.StaticProxyFactory;
import com.heaven7.java.base.util.ReflectUtils;

/**
 * Created by heaven7 on 2019/6/10.
 */
public final class InstrumentationHook {

    public static Instrumentation hookInstrumentation(Context context, StaticProxyFactory<Instrumentation> callback) {
        //Class<?> contextImplClass = Class.forName("android.app.ContextImpl");
        //Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = ReflectUtils.getVirtualFieldValue(context, "mMainThread");
        Object instrumentation = ReflectUtils.getVirtualFieldValue(activityThread, "mInstrumentation");
        Instrumentation target = callback.createProxy(context, (Instrumentation) instrumentation);
        ReflectUtils.setVirtualFieldValue("mInstrumentation",
                target, activityThread);
        return target;
    }
}