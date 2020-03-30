package ru.imv.advancedjava;

import java.util.stream.IntStream;

/**
 * Synchronized, version II
 */
public class SynchronizedIIApp {
    private volatile int counter;

    public SynchronizedIIApp() {
    }

    public static void main(String[] args) {
        SynchronizedIIApp app = new SynchronizedIIApp();
        app.doWork();
    }

    private void incrementCounter() {
        synchronized (this) {
            ++counter;
        }
    }

    public void doWork() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                IntStream.range(0, 10000)
                        .forEach(i -> incrementCounter());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                IntStream.range(0, 10000)
                        .forEach(i -> incrementCounter());
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter);
    }
}
