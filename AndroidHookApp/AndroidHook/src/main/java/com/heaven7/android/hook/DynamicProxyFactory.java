package com.heaven7.android.hook;

import android.content.Context;

import java.lang.reflect.InvocationHandler;

/**
 * the dynamic proxy factory
 * Created by heaven7 on 2019/6/11.
 */
public interface DynamicProxyFactory {

    /**
     * create proxy for target object.
     * @param context the context
     * @param src the object to proxy
     * @return the invocation handler
     */
    InvocationHandler createProxy(Context context, Object src);
}
