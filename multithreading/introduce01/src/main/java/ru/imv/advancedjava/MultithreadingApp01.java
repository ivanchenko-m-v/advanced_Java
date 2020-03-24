package ru.imv.advancedjava;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class MultithreadingApp01 {
    public static void main(String[] args) throws InterruptedException {
//        Thread t = new MiThread();
//        t.setName("MiThread 01");
//
//        Thread t1 = new MiThread();
//        t1.setName("MiThread 02");
//
//        t.start();
//        t1.start();
        Thread t = new Thread(new Runner());
        t.setName("MiThread 01");

        Thread t1 = new Thread(new Runner());
        t1.setName("MiThread 02");

        t.start();
        t1.start();

        System.out.println("Message from main thread " + Thread.currentThread().getName());
//        System.out.println("I'm going to sleep " + LocalDateTime.now());
//        Thread.sleep(3000);
//        System.out.println("I'm awake " + LocalDateTime.now());
    }
}

class MiThread extends Thread {
    @Override
    public void run() {
        IntStream.range(0, 1000)
                .forEach(value -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + value);
                });
    }
}


class Runner implements Runnable {
    @Override
    public void run() {
        IntStream.range(0, 1000)
                .forEach(value -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + value);
                });
    }
}
