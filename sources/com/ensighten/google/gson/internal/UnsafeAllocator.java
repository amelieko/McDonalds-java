package com.ensighten.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class UnsafeAllocator {

    /* renamed from: com.ensighten.google.gson.internal.UnsafeAllocator$4 */
    static class C17944 extends UnsafeAllocator {
        C17944() {
        }

        public final <T> T newInstance(Class<T> c) {
            throw new UnsupportedOperationException("Cannot allocate " + c);
        }
    }

    public abstract <T> T newInstance(Class<T> cls) throws Exception;

    public static UnsafeAllocator create() {
        final Method method;
        try {
            Class cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            final Object obj = declaredField.get(null);
            method = cls.getMethod("allocateInstance", new Class[]{Class.class});
            return new UnsafeAllocator() {
                public final <T> T newInstance(Class<T> c) throws Exception {
                    return method.invoke(obj, new Object[]{c});
                }
            };
        } catch (Exception e) {
            try {
                Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
                declaredMethod.setAccessible(true);
                final int intValue = ((Integer) declaredMethod.invoke(null, new Object[]{Object.class})).intValue();
                method = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Integer.TYPE});
                method.setAccessible(true);
                return new UnsafeAllocator() {
                    public final <T> T newInstance(Class<T> c) throws Exception {
                        return method.invoke(null, new Object[]{c, Integer.valueOf(intValue)});
                    }
                };
            } catch (Exception e2) {
                try {
                    final Method declaredMethod2 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Class.class});
                    declaredMethod2.setAccessible(true);
                    return new UnsafeAllocator() {
                        public final <T> T newInstance(Class<T> c) throws Exception {
                            return declaredMethod2.invoke(null, new Object[]{c, Object.class});
                        }
                    };
                } catch (Exception e3) {
                    return new C17944();
                }
            }
        }
    }
}
