package com.allen_anker.reflection;

import java.lang.reflect.Field;

public class ReflectionUtil {
    public static void setPropertyName(Object obj, String propertyName, Object value) throws NoSuchFieldException,
            IllegalAccessException {
        Class className = obj.getClass();
        Field field = className.getDeclaredField(propertyName);
        field.setAccessible(true);
        field.set(obj, value);
    }
}
