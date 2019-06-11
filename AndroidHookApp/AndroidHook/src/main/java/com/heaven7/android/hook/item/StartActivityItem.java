package com.heaven7.android.hook.item;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class StartActivityItem {

    public final ComponentName focus;
    public final ComponentName target;

    private StartActivityItem(ComponentName focus, ComponentName target) {
        this.focus = focus;
        this.target = target;
    }

    public static StartActivityItem of(ComponentName focus, ComponentName target){
        return new StartActivityItem(focus, target);
    }
    public static StartActivityItem of(Context context, Class<? extends Activity> focusClass, Class<? extends Activity> targetClass){
        return new StartActivityItem(
                new ComponentName(context, focusClass),
                new ComponentName(context, targetClass));
    }
    public static StartActivityItem of(Context context, String focusClass, String targetClass){
        return new StartActivityItem(
                new ComponentName(context, focusClass),
                new ComponentName(context, targetClass));
    }
}
