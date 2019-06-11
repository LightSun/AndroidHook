package com.heaven7.android.hook;

import com.heaven7.java.base.util.ReflectUtils;

import java.lang.reflect.Method;

public interface ProxyHookCallback {

    boolean shouldHook(Object src, Method method, Object[] args);

    Object invokeHook(Object src, Object dst, Method method, Object[] args) throws ReflectUtils.ReflectException;
}