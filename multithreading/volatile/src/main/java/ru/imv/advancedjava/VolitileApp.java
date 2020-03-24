package ru.imv.advancedjava;

import java.util.Scanner;

/**
 * Hello world!
 */
public class VolitileApp {
    public static void main(String[] args) {
        MiThread t = new MiThread();
        t.setName("MiThread 01");
        t.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        t.shutdown();
    }
}

class MiThread extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hi from " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
        System.out.println("The thread " + Thread.currentThread().getName() + " will be closed.");
    }
}

