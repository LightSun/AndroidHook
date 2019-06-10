package com.heaven7.android.hook;

import com.heaven7.java.base.util.Predicates;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by heaven7 on 2019/6/10.
 */
public class ProxyHandler implements InvocationHandler {

    private final Object mDst;
    private final List<HookCallback> mHooks;

    public ProxyHandler(Object dst, HookCallback...callbacks) {
        this.mDst = dst;
        this.mHooks = Arrays.asList(callbacks);
    }
    @Override
    public Object invoke(Object src, Method method, Object[] args) throws Throwable {
        if(!Predicates.isEmpty(mHooks)){
            for (HookCallback callback : mHooks){
                if(callback.shouldHook(src, method, args)){
                   return callback.invokeHook(src, mDst, method, args);
                }
            }
        }
        return method.invoke(mDst, args);
    }
}
