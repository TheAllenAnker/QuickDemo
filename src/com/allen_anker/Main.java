package com.allen_anker;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int i = 0;
        while (i < 10) {
            System.out.print(fib(i) + " ");
            i++;
        }
        System.out.println();
    }

    public static int fib(int n) {
        int a = 1, b = 1;
        int result = 1;
        while (n > 1) {
            result = a + b;
            a = b;
            b = result;
            n--;
        }
        return result;
    }
}
