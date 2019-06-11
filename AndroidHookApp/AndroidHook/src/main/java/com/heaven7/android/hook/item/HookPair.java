package com.heaven7.android.hook.item;

/**
 * Created by heaven7 on 2019/6/11.
 */
public final class HookPair {

    public final String first;
    public final String second;

    public HookPair(String first, String second) {
        this.first = first;
        this.second = second;
    }
    public HookPair(Class<?> first, Class<?> second) {
        this.first = first.getName();
        this.second = second.getName();
    }
}
