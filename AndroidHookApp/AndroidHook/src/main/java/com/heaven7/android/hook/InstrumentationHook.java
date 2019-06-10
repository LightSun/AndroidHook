package com.heaven7.android.hook;

import android.app.Instrumentation;
import android.content.Context;

import com.heaven7.java.base.util.ReflectUtils;

/**
 * Created by heaven7 on 2019/6/10.
 */
public final class InstrumentationHook {

    public static void hookInstrumentation(Context context, Callback callback){
        //Class<?> contextImplClass = Class.forName("android.app.ContextImpl");
        //Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = ReflectUtils.getVirtualFieldValue(context, "mMainThread");
        Object instrumentation = ReflectUtils.getVirtualFieldValue(activityThread, "mInstrumentation");
        ReflectUtils.setVirtualFieldValue("mInstrumentation",
                callback.createInstrumentationProxy(context, instrumentation),
                activityThread);
    }

    public interface Callback {
        Instrumentation createInstrumentationProxy(Context context,Object src);
    }
}
