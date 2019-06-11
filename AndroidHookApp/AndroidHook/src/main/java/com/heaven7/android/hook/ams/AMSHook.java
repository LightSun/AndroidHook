package com.heaven7.android.hook.ams;

import android.content.Context;
import android.os.Build;

import com.heaven7.android.hook.DynamicProxyFactory;
import com.heaven7.java.base.util.ReflectUtils;

import java.lang.reflect.Proxy;

/**
 * Created by heaven7 on 2019/6/10.
 */
public final class AMSHook {

    private static final AMSHookDelegate sDelegate;

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            sDelegate = new HookAMS26();
        } else {
            sDelegate = new HookAMSBase();
        }
    }

    /**
     * hook AMS
     * @param context the context
     * @param callback the dynamic proxy factory
     * @return the hooked 'AMS'.
     * @throws ReflectUtils.ReflectException if occurs
     */
    public static Object hookAMS(Context context, DynamicProxyFactory callback) throws ReflectUtils.ReflectException {
        return sDelegate.hookAMS(context, callback);
    }

    private interface AMSHookDelegate {
        Object hookAMS(Context context, DynamicProxyFactory callback) throws ReflectUtils.ReflectException;
    }

    private static class HookAMSBase implements AMSHookDelegate {
        @Override
        public Object hookAMS(Context context,DynamicProxyFactory callback) throws ReflectUtils.ReflectException {
            try {
                Object defaultSingleton = null;
                Class<?> activityManagerNativeClazz = Class.forName("android.app.ActivityManagerNative");
                defaultSingleton = ReflectUtils.getStaticFieldValue(activityManagerNativeClazz, "gDefault");

                Class<?> iActivityManagerClazz = Class.forName("android.app.IActivityManager");
                Object iActivityManager = ReflectUtils.getVirtualFieldValue(defaultSingleton, "mInstance");

                Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                        new Class<?>[]{iActivityManagerClazz}, callback.createProxy(context, iActivityManager));
                ReflectUtils.setVirtualFieldValue("mInstance", proxy, defaultSingleton);
                return proxy;
            } catch (ClassNotFoundException e) {
                throw new ReflectUtils.ReflectException(e);
            }
        }
    }

    private static class HookAMS26 implements AMSHookDelegate {
        @Override
        public Object hookAMS(Context context,DynamicProxyFactory callback) throws ReflectUtils.ReflectException {
            try {
                Object defaultSingleton = null;

                Class<?> activityManageClazz = Class.forName("android.app.ActivityManager");
                defaultSingleton = ReflectUtils.getStaticFieldValue(activityManageClazz, "IActivityManagerSingleton");

                Class<?> iActivityManagerClazz = Class.forName("android.app.IActivityManager");
                Object iActivityManager = ReflectUtils.getVirtualFieldValue(defaultSingleton, "mInstance");

                Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                        new Class<?>[]{iActivityManagerClazz}, callback.createProxy(context,iActivityManager));
                ReflectUtils.setVirtualFieldValue("mInstance", proxy, defaultSingleton);
                return proxy;
            } catch (ClassNotFoundException e) {
                throw new ReflectUtils.ReflectException(e);
            }
        }
    }
}
