package com.allen_anker.generics;

public class InstantiateGenericType {
    public static void main(String[] args) {
        ClassAsFactory<Employee> fe =
                new ClassAsFactory<>(Employee.class);
        System.out.println("ClassAsFactory<Employee> succeed");
        try {
            ClassAsFactory<Integer> fi =
                    new ClassAsFactory<>(Integer.class);
        } catch (Exception e) {
            System.out.println("ClassFactory<Integer> failed");
        }
    }
}

class ClassAsFactory<T> {
    T x;

    public ClassAsFactory(Class<T> kind) {
        try {
            this.x = kind.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Employee {
}
