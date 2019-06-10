package com.heaven7.android.hook;

import android.os.Build;

import com.heaven7.java.base.util.ReflectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by heaven7 on 2019/6/10.
 */
public abstract class AMSHook {

    public static Object hookAMS(Callback callback) throws ReflectUtils.ReflectException {
        AMSHookDelegate delegate;
        if (Build.VERSION.SDK_INT >= 26) {
            delegate = new HookAMS26();
        } else {
            delegate = new HookAMSBase();
        }
        return delegate.hookAMS(callback);
    }

    public interface Callback {
        InvocationHandler createAMSProxy(Object iam);
    }

    private interface AMSHookDelegate {
        Object hookAMS(Callback callback) throws ReflectUtils.ReflectException;
    }

    private static class HookAMSBase implements AMSHookDelegate {
        @Override
        public Object hookAMS(Callback callback) throws ReflectUtils.ReflectException {
            try {
                Object defaultSingleton = null;
                Class<?> activityManagerNativeClazz = Class.forName("android.app.ActivityManagerNative");
                defaultSingleton = ReflectUtils.getStaticFieldValue(activityManagerNativeClazz, "gDefault");

                Class<?> iActivityManagerClazz = Class.forName("android.app.IActivityManager");
                Object iActivityManager = ReflectUtils.getVirtualFieldValue(defaultSingleton, "mInstance");

                Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                        new Class<?>[]{iActivityManagerClazz}, callback.createAMSProxy(iActivityManager));
                ReflectUtils.setVirtualFieldValue("mInstance", proxy, defaultSingleton);
                return proxy;
            } catch (ClassNotFoundException e) {
                throw new ReflectUtils.ReflectException(e);
            }
        }
    }

    private static class HookAMS26 implements AMSHookDelegate {
        @Override
        public Object hookAMS(Callback callback) throws ReflectUtils.ReflectException {
            try {
                Object defaultSingleton = null;

                Class<?> activityManageClazz = Class.forName("android.app.ActivityManager");
                defaultSingleton = ReflectUtils.getStaticFieldValue(activityManageClazz, "IActivityManagerSingleton");

                Class<?> iActivityManagerClazz = Class.forName("android.app.IActivityManager");
                Object iActivityManager = ReflectUtils.getVirtualFieldValue(defaultSingleton, "mInstance");

                Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                        new Class<?>[]{iActivityManagerClazz}, callback.createAMSProxy(iActivityManager));
                ReflectUtils.setVirtualFieldValue("mInstance", proxy, defaultSingleton);
                return proxy;
            } catch (ClassNotFoundException e) {
                throw new ReflectUtils.ReflectException(e);
            }
        }
    }
}
