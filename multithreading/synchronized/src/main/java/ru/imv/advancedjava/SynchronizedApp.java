package ru.imv.advancedjava;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class SynchronizedApp {
    private volatile int counter;
//    private AtomicInteger counter = new AtomicInteger();

    public SynchronizedApp() {
        /* atomic
        counter.set(0);
         */
    }

    public static void main(String[] args) {
        SynchronizedApp app = new SynchronizedApp();
        app.doWork();
    }

    private synchronized void incrementCounter() {
        ++counter;
    }
    /**
     * atomic
    private void incrementCounter() {
        //++counter;
        counter.getAndIncrement();
    }
     */

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
