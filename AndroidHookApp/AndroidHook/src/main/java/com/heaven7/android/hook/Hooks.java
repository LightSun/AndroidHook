package com.heaven7.android.hook;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import com.heaven7.android.hook.ams.AMSHook;
import com.heaven7.android.hook.ams.StartActivityAMSHookCallback;
import com.heaven7.android.hook.instrumentation.InstrumentationHook;
import com.heaven7.android.hook.instrumentation.InstrumentationProxy;
import com.heaven7.android.hook.item.HookPair;
import com.heaven7.android.hook.item.StartActivityItem;
import com.heaven7.java.base.anno.Nullable;
import com.heaven7.java.base.util.Throwables;

import java.lang.reflect.InvocationHandler;

/**
 * Created by heaven7 on 2019/6/10.
 */
public final class Hooks {

    /**
     * hook for start activity.
     * @param context the context
     * @param items the start activity items
     * @see android.app.Activity#startActivity(Intent)
     */
    public static void ofStartActivity(Context context, final StartActivityItem... items) {
        ofStartActivity(context, null, items);
    }
    /**
     * hook for start activity.
     * @param context the context
     * @param pkgItem the package item which focus on package name. can be null.
     * @param items the start activity items which focus on specify class
     * @see android.app.Activity#startActivity(Intent)
     * @since 1.0.1
     */
    public static void ofStartActivity(Context context, @Nullable final StartActivityItem pkgItem, final StartActivityItem[] items) {
        Throwables.checkEmpty(items);
        AMSHook.hookAMS(context, new DynamicProxyFactory() {
            @Override
            public InvocationHandler createProxy(Context context, Object src) {
                return new ProxyHandler(src, new StartActivityAMSHookCallback(pkgItem, items));
            }
        });
        InstrumentationHook.hookInstrumentation(context, new StaticProxyFactory<Instrumentation>() {
            @Override
            public Instrumentation createProxy(Context context, Instrumentation src) {
                return new InstrumentationProxy(context, src);
            }
        });
    }
    /**
     * hook for start activity.
     * @param context the context
     * @param activityPairs the activity pairs
     * @see android.app.Activity#startActivity(Intent)
     */
    public static void ofStartActivity(Context context, HookPair... activityPairs) {
        ofStartActivity(context, null, activityPairs);
    }
    /**
     * hook for start activity.
     * @param context the context
     * @param pkgPair the pair which focus on the package name. or null if not need.
     *                for this 'pair.first' is package name. and 'pair.second' is target class name.
     * @param activityPairs the activity pairs
     * @see android.app.Activity#startActivity(Intent)
     * @since 1.0.1
     */
    public static void ofStartActivity(Context context, @Nullable HookPair pkgPair, HookPair[] activityPairs) {
        StartActivityItem pkg = null;
        if(pkgPair != null){
            pkg = StartActivityItem.ofFocusPackage(context, pkgPair.first, pkgPair.second);
        }
        StartActivityItem[] saitems = new StartActivityItem[activityPairs.length];
        for (int i = 0, size = activityPairs.length; i < size; i++) {
            saitems[i] = StartActivityItem.of(context, activityPairs[i].first, activityPairs[i].second);
        }
        ofStartActivity(context, pkg, saitems);
    }
}
