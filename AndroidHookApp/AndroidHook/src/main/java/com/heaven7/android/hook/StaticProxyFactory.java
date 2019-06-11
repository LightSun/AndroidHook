package com.heaven7.android.hook;

import android.content.Context;

/**
 * Created by heaven7 on 2019/6/11.
 */
public interface StaticProxyFactory<T> {

    T createProxy(Context context, T iam);
}
