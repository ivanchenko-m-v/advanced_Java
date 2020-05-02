package ru.imv.advancedjava;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * ThreadTerminateApp
 */
public class ThreadTerminateApp {
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            Random random = new Random();

            @Override
            public void run() {

                for (int i = 0; i < 1_000_000_000; ++i) {
                    System.out.print("\r" + LocalDateTime.now());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        //при прерывании спящего потока
                        System.out.println("\nThread was interrupted");
                        break;
                    }
//                    if (Thread.currentThread().isInterrupted()) {
//                        //при прерывании работающего потока
//                        System.out.println("\nThread was interrupted");
//                        return;
//                    }
                    Math.sin(random.nextDouble());
                }
            }
        });

        System.out.println("Starting thread");

        t.start();

        Thread.currentThread().sleep(3000);
        t.interrupt();

        t.join();

        System.out.println("Thread has finished");
    }
}
