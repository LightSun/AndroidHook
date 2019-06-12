package com.heaven7.android.hook.item;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;

import com.heaven7.java.base.util.Throwables;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class StartActivityItem {

    public final ComponentName focus;
    public final ComponentName target;
    public final String focusPackage;

    private StartActivityItem(ComponentName focus, ComponentName target) {
        this(focus, target, null);
        Throwables.checkNull(focus);
        Throwables.checkNull(target);
    }
    private StartActivityItem(ComponentName focus, ComponentName target, String focusPackage) {
        this.focus = focus;
        this.target = target;
        this.focusPackage = focusPackage;
    }
    private StartActivityItem(ComponentName target, String focusPackage) {
        this(null, target, focusPackage);
        Throwables.checkNull(target);
        Throwables.checkNull(focusPackage);
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

    /**
     * create start activity item which focus on package name of src activity.
     * @param context the context
     * @param focusPackage the package name of focus
     * @param targetClass the target class to replace.which used for system verify.
     * @return the item
     */
    public static StartActivityItem ofFocusPackage(Context context, String focusPackage, String targetClass){
        return new StartActivityItem(
                new ComponentName(context, targetClass),
                focusPackage);
    }
}
