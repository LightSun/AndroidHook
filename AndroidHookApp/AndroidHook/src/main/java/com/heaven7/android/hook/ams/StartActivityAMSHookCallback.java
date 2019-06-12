package com.heaven7.android.hook.ams;

import android.content.ComponentName;
import android.content.Intent;

import com.heaven7.android.hook.HookCons;
import com.heaven7.android.hook.ProxyHookCallback;
import com.heaven7.android.hook.item.StartActivityItem;
import com.heaven7.android.hook.utils.HookUtils;
import com.heaven7.java.base.anno.Nullable;
import com.heaven7.java.base.util.Predicates;
import com.heaven7.java.base.util.ReflectUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class StartActivityAMSHookCallback implements ProxyHookCallback {

    private final StartActivityItem mPackageItem;
    private final List<StartActivityItem> mItems;

    /**
     * create StartActivity hook callback for target arguments
     * @param pkgItem the package item which focus on package.
     * @param items the items which focus on specific class.
     */
    public StartActivityAMSHookCallback(@Nullable StartActivityItem pkgItem, StartActivityItem...items) {
        this.mPackageItem = pkgItem;
        this.mItems = Arrays.asList(items);
    }

    @Override
    public boolean shouldHook(Object src, Method method, Object[] args) {
        return !Predicates.isEmpty(mItems) && method.getName().equals("startActivity");
    }

    @Override
    public Object invokeHook(Object src, Object dst, Method method, Object[] args) throws ReflectUtils.ReflectException{
        int[] index_arr = new int[1];
        Intent intent = HookUtils.getIntent(args, index_arr);
        if(intent != null){
            int index = index_arr[0];
            //find matched item.
            StartActivityItem targetItem = null;
            ComponentName component = intent.getComponent();
            if(component != null){
                for (StartActivityItem item : mItems){
                    if(component.equals(item.focus)){
                        targetItem = item;
                        break;
                    }
                }
                //specific can't find and has focus package
                if(targetItem == null && mPackageItem != null && mPackageItem.focusPackage != null
                        && component.getClassName().startsWith(mPackageItem.focusPackage)){
                    targetItem = mPackageItem;
                }
                // find. replace pkg. and put extra . replace intent args.
                if(targetItem != null){
                    // find. replace pkg. and put extra . replace intent args.
                    Intent target = new Intent();
                    //set new component (registered in xml)for platform verify
                    target.setComponent(new ComponentName(component.getPackageName(), targetItem.target.getClassName()));
                    target.putExtra(HookCons.KEY_SRC_INTENT, intent);
                    target.putExtra(HookCons.KEY_TYPE, HookCons.TYPE_REPLACE_ACTIVITY);
                    //the extras should be move to target intent
                    if(intent.getExtras() != null){
                        target.putExtras(intent.getExtras());
                        //intent.getExtras().clear();
                    }
                    //set intent.
                    args[index] = target;
                }
            }
        }
        try {
            return method.invoke(dst, args);
        } catch (Exception e) {
            throw new ReflectUtils.ReflectException(e);
        }
    }
}
