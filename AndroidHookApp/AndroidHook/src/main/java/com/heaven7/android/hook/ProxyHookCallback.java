package com.heaven7.android.hook;

import com.heaven7.java.base.util.ReflectUtils;

import java.lang.reflect.Method;

/**
 * the proxy hook callback
 * @author heaven7
 */
public interface ProxyHookCallback {

    /**
     * indicate should hook or not.
     * @param src the object from {@linkplain java.lang.reflect.InvocationHandler#invoke(Object, Method, Object[])}
     * @param method the method object
     * @param args the arguments
     * @return true if should hook
     */
    boolean shouldHook(Object src, Method method, Object[] args);

    /**
     * invoke method for hook
     * @param src the src object of method invoke on
     * @param dst the dest object to invoke hook
     * @param method the method
     * @param args the args
     * @return the result of invoke method
     * @throws ReflectUtils.ReflectException if occurs
     */
    Object invokeHook(Object src, Object dst, Method method, Object[] args) throws ReflectUtils.ReflectException;
}