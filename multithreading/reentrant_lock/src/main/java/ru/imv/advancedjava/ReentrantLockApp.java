package ru.imv.advancedjava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class ReentrantLockApp {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();

        Thread t1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        task.firstThread();
                    }
                });

        Thread t2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        task.secondThread();
                    }
                });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        task.showCounter();
    }
}

class Task {
    private int counter;
    private Lock lock = new ReentrantLock();

    private void increment() {
        IntStream.range(0, 10000)
                .forEach(i -> {
                    lock.lock();
                    ++counter;
                    lock.unlock();
                });
    }

    public void firstThread() {
//        lock.lock();
        increment();
//        lock.unlock();
    }

    public void secondThread() {
//        lock.lock();
        increment();
//        lock.unlock();
    }

    public void showCounter() {
        System.out.println("counter is " + counter);
    }
}