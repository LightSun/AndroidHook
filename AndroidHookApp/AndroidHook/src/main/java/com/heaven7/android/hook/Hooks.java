package com.heaven7.android.hook;

import android.app.Instrumentation;
import android.content.Context;

import com.heaven7.android.hook.impl.StartActivityAMSHookCallback;
import com.heaven7.android.hook.item.StartActivityItem;

import java.lang.reflect.InvocationHandler;

/**
 * Created by heaven7 on 2019/6/10.
 */
public final class Hooks {

    public static void ofStartActivity(Context context, final StartActivityItem... items){
         AMSHook.hookAMS(new AMSHook.Callback() {
             @Override
             public InvocationHandler createAMSProxy(Object iam) {
                 return new ProxyHandler(iam, new StartActivityAMSHookCallback(items));
             }
         });
         InstrumentationHook.hookInstrumentation(context, new InstrumentationHook.Callback() {
             @Override
             public Instrumentation createInstrumentationProxy(Context context, Object src) {
                 return new InstrumentationProxy(context, (Instrumentation) src);
             }
         });
    }
}
