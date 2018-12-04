package com.allen_anker.thread;

import java.util.concurrent.Semaphore;

public class AbnormalSemaphoreSample {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new MyWorker(semaphore));
            t.start();
        }

        System.out.println("In Action");
        semaphore.release(5);
        System.out.println("Wait for all permits off");
        while (semaphore.availablePermits() != 0) {
            Thread.sleep(100L);
        }
        System.out.println("In Action Again");
        semaphore.release(5);
    }
}

class MyWorker implements Runnable {
    private Semaphore semaphore;

    public MyWorker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " Executed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}