package com.heaven7.android.hook.item;

import android.content.ComponentName;
import android.content.Intent;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class StartActivityItem {

    public final ComponentName focus;
    public final Intent targetIntent;

    public StartActivityItem(ComponentName focus, Intent target) {
        this.focus = focus;
        this.targetIntent = target;
    }
}
