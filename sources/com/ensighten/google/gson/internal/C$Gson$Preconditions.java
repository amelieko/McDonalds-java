package com.ensighten.google.gson.internal;

/* renamed from: com.ensighten.google.gson.internal.$Gson$Preconditions */
public final class C$Gson$Preconditions {
    public static <T> T checkNotNull(T obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException();
    }

    public static void checkArgument(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }
}
