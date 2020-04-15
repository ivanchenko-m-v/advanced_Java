package ru.imv.advancedjava;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * ProducerConsumer2App
 */
public class ProducerConsumer2App {

    public static void main(String[] args) {
        final ProducerConsumer pc = new ProducerConsumer();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
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

class ProducerConsumer {
    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();
    private Random random = new Random();

    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while (queue.size() == LIMIT) {
                    lock.wait();
                }
                queue.offer(value++);
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (queue.size() == 0) {
                    lock.wait();
                }
                int value = queue.poll();
                System.out.println(value);
                System.out.println("Queue size is " + queue.size());

                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }
    }
}