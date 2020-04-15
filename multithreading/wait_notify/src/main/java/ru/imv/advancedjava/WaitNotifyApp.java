package ru.imv.advancedjava;

import java.util.Scanner;

/**
 * WaitNotifyApp
 */
public class WaitNotifyApp {
    public static void main(String[] args) {
        final WaitAndNotify wn = new WaitAndNotify();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    }
}

class WaitAndNotify {
    private final Object lock = new Object();

    public void produce() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Producer thread started...");
            // обязательно вызывать wait/notify для объекта, на мониторе которого производится синхронизация
            // (указанного в synchronized(...))
            lock.wait();
            System.out.println("Producer thread resumed...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (lock) {
            System.out.println("Waiting for <Enter>-key press");
            scanner.nextLine();
            lock.notify();
            Thread.sleep(5000);
            System.out.println("Consumer thread resumed...");
        }
    }
}