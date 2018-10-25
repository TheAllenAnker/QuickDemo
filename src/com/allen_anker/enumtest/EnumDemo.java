package com.allen_anker.enumtest;

public class EnumDemo {
    public static void main(String[] args) {

    }
}

enum Week {
    MON("Monday"), TUE("Tuesday"), WED("Wednesday"), THUR("Thursday"), FRI("Friday");

    private String name;

    private Week(String name) {
        this.name = name;
    }
}
