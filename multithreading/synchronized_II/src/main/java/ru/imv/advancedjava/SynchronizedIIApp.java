package ru.imv.advancedjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Synchronized, version II
 */
public class SynchronizedIIApp {
    public static void main(String[] args) throws InterruptedException {
        new Worker().main();
    }
}

class Worker {
    private static final Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    private void addToList(List<Integer> list, int rndSeed) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized(list) {
            list.add(random.nextInt(rndSeed));
        }
    }

    private void doWork() {
        for (int counter = 0; counter < 1000; ++counter) {
            addToList(list1, 100);
            addToList(list2, 100);
        }
    }

    public void main() {
        long before = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
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

        long after = System.currentTimeMillis();
        System.out.println("Program took " + (after - before) + "ms to run");

        System.out.println("List1 (" + list1.size() + "):" + list1);
        System.out.println("List2 (" + list2.size() + "):" + list2);
    }
}