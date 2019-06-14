package com.heaven7.android.hook.utils;

import android.content.Intent;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by heaven7 on 2019/6/10.
 */
public final class HookUtils {

    /**
     * get intent argument from target args
     * @param args the args
     * @param arr_index the index array. length = 1.
     * @return the intent if found. or else null .
     */
    public static Intent getIntent(Object[] args, int[] arr_index){
        int index = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Intent) {
                index = i;
                break;
            }
        }
        if(index < 0){
            return null;
        }
        if(arr_index != null){
            arr_index[0] = index;
        }
        return (Intent) args[index];
    }
    public static void copyFieldValue(Object src, Object dst) {
        Class<?> clazz = src.getClass();
        do {
            copyFieldValueImpl(src, dst, clazz);
            clazz = clazz.getSuperclass();
            if(clazz == null || clazz == Object.class){
                break;
            }
        }while (true);
    }

    private static void copyFieldValueImpl(Object src, Object dst, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field f: fields){
                if(Modifier.isFinal(f.getModifiers()) || Modifier.isStatic(f.getModifiers())){
                    continue;
                }
                f.setAccessible(true);
                f.set(dst, f.get(src));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
