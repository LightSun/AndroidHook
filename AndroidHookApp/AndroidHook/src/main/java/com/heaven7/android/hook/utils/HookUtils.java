package com.heaven7.android.hook.utils;

import android.content.Intent;

/**
 * Created by heaven7 on 2019/6/10.
 */
public final class HookUtils {

    public static Intent getIntent(Object[] args){
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
        return (Intent) args[index];
    }
}
