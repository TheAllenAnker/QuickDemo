package com.allen_anker.reflection;

import com.allen_anker.bean.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class className = Class.forName("com.allen_anker.bean.Student");
        Student student = (Student) className.newInstance();
        Field nameField = className.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(student, "Allen Anker");
        System.out.println(student);
        Constructor constructor = className.getDeclaredConstructor(String.class, String.class, int.class);
        Student student1 = (Student) constructor.newInstance("Allen Anker", "Male", 19);
        System.out.println(student1);

        Method setNameMethod = className.getMethod("getName");
        System.out.println(setNameMethod.invoke(student));
    }
}
