package com.allen_anker.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Demo of two threads communicating.
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
//        demo2();
        demo3();
    }

    private static void demo1() {
        MyPrinter1 printer = new MyPrinter1();

        new Thread(() -> {
            while (true) {
                printer.print1();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                printer.print2();
            }
        }).start();
    }

    private static void demo2() {
        MyPrinter2 printer = new MyPrinter2();

        new Thread(() -> {
            while (true) {
                printer.print1();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                printer.print2();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                printer.print3();
            }
        }).start();
    }

    private static void demo3() {
        MyPrinter3 printer = new MyPrinter3();

        new Thread(() -> {
            while (true) {
                printer.print1();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                printer.print2();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                printer.print3();
            }
        }).start();
    }
}

class MyPrinter1 {

    private int flag = 1;

    public void print1() {
        synchronized (this) {
            if (flag != 1) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("This is message1 part 1.");
            System.out.println("This is message1 part 2.");
            flag = 2;
            this.notify();
        }
    }

    public void print2() {
        synchronized (this) {
            if (flag != 2) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("This is message2 part 1.");
            System.out.println("This is message2 part 2.");
            flag = 1;
            this.notify();
        }
    }
}

class MyPrinter2 {

    private int flag = 1;

    public void print1() {
        synchronized (this) {
            if (flag != 1) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("This is message1 part 1.");
            System.out.println("This is message1 part 2.");
            flag = 2;
            this.notifyAll();
        }
    }

    public void print2() {
        synchronized (this) {
            if (flag != 2) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("This is message2 part 1.");
            System.out.println("This is message2 part 2.");
            flag = 3;
            this.notifyAll();
        }
    }

    public void print3() {
        synchronized (this) {
            while (flag != 3) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("This is message3 part 1.");
            System.out.println("This is message3 part 2.");
            flag = 1;
            this.notifyAll();
        }
    }
}

/**
 * Now let's try out the java 1.5 new feature. ReentrantLock
 */
class MyPrinter3 {
    private ReentrantLock lock;
    private Condition printC1;
    private Condition printC2;
    private Condition printC3;
    private int flag = 1;

    public MyPrinter3() {
        lock = new ReentrantLock();
        printC1 = lock.newCondition();
        printC2 = lock.newCondition();
        printC3 = lock.newCondition();
    }

    public void print1() {
        lock.lock();
        if (flag != 1) {
            try {
                printC1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("This is message1 part 1.");
        System.out.println("This is message1 part 2.");
        flag = 2;
        printC2.signal();
        lock.unlock();
    }

    public void print2() {
        lock.lock();
        if (flag != 2) {
            try {
                printC2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("This is message2 part 1.");
        System.out.println("This is message2 part 2.");
        flag = 3;
        printC3.signal();
        lock.unlock();
    }

    public void print3() {
        lock.lock();
        while (flag != 3) {
            try {
                printC3.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("This is message3 part 1.");
        System.out.println("This is message3 part 2.");
        flag = 1;
        printC1.signal();
        lock.unlock();
    }
}