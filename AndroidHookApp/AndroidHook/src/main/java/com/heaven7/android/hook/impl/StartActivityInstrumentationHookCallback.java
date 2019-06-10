package com.heaven7.android.hook.impl;

import android.content.Intent;

import com.heaven7.android.hook.HookCallback;
import com.heaven7.android.hook.HookCons;
import com.heaven7.android.hook.utils.HookUtils;
import com.heaven7.java.base.util.ReflectUtils;

import java.lang.reflect.Method;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class StartActivityInstrumentationHookCallback implements HookCallback {

    //Activity newActivity(ClassLoader cl, String className, Intent intent)
    @Override
    public boolean shouldHook(Object src, Method method, Object[] args) {
        return method.getName().equals("newActivity");
    }

    @Override
    public Object invokeHook(Object src, Object dst, Method method, Object[] args) throws ReflectUtils.ReflectException {
        Intent intent = HookUtils.getIntent(args);
        int type = intent.getIntExtra(HookCons.KEY_TYPE, 0);

        if(type == HookCons.TYPE_REPLACE_ACTIVITY){
            Intent srcIntent = intent.getParcelableExtra(HookCons.KEY_SRC_INTENT);
            args[1] = srcIntent.getComponent().getClassName();
        }
        try {
            return method.invoke(dst, args);
        } catch (Exception e) {
            throw new ReflectUtils.ReflectException(e);
        }
    }
}
