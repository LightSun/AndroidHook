package com.heaven7.android.hook.utils;

import com.heaven7.java.base.util.ReflectUtils;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class AndroidHookException extends ReflectUtils.ReflectException {

    public AndroidHookException() {
    }

    public AndroidHookException(String message) {
        super(message);
    }

    public AndroidHookException(String message, Throwable cause) {
        super(message, cause);
    }

    public AndroidHookException(Throwable cause) {
        super(cause);
    }
}
