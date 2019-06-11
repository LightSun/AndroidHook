package com.heaven7.android.hook;

import android.content.Context;

/**
 * the static proxy factory
 * Created by heaven7 on 2019/6/11.
 */
public interface StaticProxyFactory<T> {

    /**
     * create proxy for target object
     * @param context the context
     * @param src the src object
     * @return the proxy
     */
    T createProxy(Context context, T src);
}
