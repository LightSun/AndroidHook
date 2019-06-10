package com.heaven7.android.hook.impl;

import android.content.ComponentName;
import android.content.Intent;

import com.heaven7.android.hook.HookCallback;
import com.heaven7.android.hook.HookCons;
import com.heaven7.android.hook.utils.AndroidHookException;
import com.heaven7.java.base.util.Predicates;
import com.heaven7.java.base.util.ReflectUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class StartActivityAMSHookCallback implements HookCallback {

    private final List<HookItem> mItems;

    public StartActivityAMSHookCallback(HookItem...items) {
        this.mItems = Arrays.asList(items);
    }

    @Override
    public boolean shouldHook(Object src, Method method, Object[] args) {
        return !Predicates.isEmpty(mItems) && method.getName().equals("startActivity");
    }

    @Override
    public Object invokeHook(Object src, Object dst, Method method, Object[] args) throws ReflectUtils.ReflectException{
        Intent intent;
        int index = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Intent) {
                index = i;
                break;
            }
        }
        if(index < 0){
            throw new AndroidHookException("can't find intent for startActivity.");
        }
        intent = (Intent) args[index];
        ComponentName component = intent.getComponent();
        //find item
        HookItem targetItem = null;
        for (HookItem item : mItems){
            if(component.equals(item.focus)){
                targetItem = item;
                break;
            }
        }
        // find. replace pkg. and put extra . replace intent args.
        if(targetItem != null){
            // find. replace pkg. and put extra . replace intent args.
            Intent target = targetItem.targetIntent;
            ComponentName targetCN = target.getComponent();
            target.setComponent(new ComponentName(component.getPackageName(), targetCN.getClassName()));
            target.putExtra(HookCons.KEY_SRC_INTENT, intent);
            target.putExtra(HookCons.KEY_TYPE, HookCons.TYPE_REPLACE_ACTIVITY);
            //set intent.
            args[index] = target;
        }
        try {
            return method.invoke(dst, args);
        } catch (Exception e) {
            throw new ReflectUtils.ReflectException(e);
        }
    }

    public static class HookItem{
        final ComponentName focus;
        final Intent targetIntent;

        public HookItem(ComponentName focus, Intent target) {
            this.focus = focus;
            this.targetIntent = target;
        }
    }
}
