package com.heaven7.android.hook.instrumentation;

import android.app.Instrumentation;
import android.content.Context;
import android.content.ContextWrapper;

import com.heaven7.android.hook.StaticProxyFactory;
import com.heaven7.java.base.util.ReflectUtils;

/**
 * Created by heaven7 on 2019/6/10.
 */
public final class InstrumentationHook {

    /**
     * hook instrumentation
     * @param context the context
     * @param callback the factory
     * @return the new Instrumentation
     */
    public static Instrumentation hookInstrumentation(Context context, StaticProxyFactory<Instrumentation> callback) {
        Context base = context;
        while (base instanceof ContextWrapper){
            base = ((ContextWrapper) base).getBaseContext();
        }
        //Class<?> contextImplClass = Class.forName("android.app.ContextImpl");
        //Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = ReflectUtils.getVirtualFieldValue(base, "mMainThread");
        Object instrumentation = ReflectUtils.getVirtualFieldValue(activityThread, "mInstrumentation");
        Instrumentation target = callback.createProxy(context, (Instrumentation) instrumentation);
        ReflectUtils.setVirtualFieldValue("mInstrumentation",
                target, activityThread);
        return target;
    }
}
