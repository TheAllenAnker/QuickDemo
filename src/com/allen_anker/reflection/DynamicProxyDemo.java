package com.allen_anker.reflection;

import com.allen_anker.bean.Student;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void main(String[] args) {
        Student student = new Student("Allen Anker", "Male", 19);
        MyInvocationHandler handler = new MyInvocationHandler(student);
        // this only works with interface, Student is not an interface, so it doesn't work
        Student s = (Student) Proxy.newProxyInstance(student.getClass().getClassLoader(), student.getClass().getInterfaces(),
                handler);
        s.introduceYourself();
    }
}
