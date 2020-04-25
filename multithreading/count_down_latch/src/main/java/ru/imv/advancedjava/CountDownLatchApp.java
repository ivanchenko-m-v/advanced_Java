package ru.imv.advancedjava;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class CountDownLatchApp {

    public static void main(String[] args) throws InterruptedException {
        main1();
    }

    /*
        Потоки выполняют работу, приостанавливают главный поток, пока не откроется "защёлка"
     */
    private static void main0() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        IntStream.range(0, 3)
                .forEach(i -> executorService.submit(new Processor(countDownLatch)));

        executorService.shutdown();

        countDownLatch.await();
        System.out.println("Latch has been opened, main thread is proceeding");
    }

    /*
        Главный поток выполняет работу, приостанавливает другие потоки, пока не откроется "защёлка"
     */
    private static void main1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        IntStream.range(0, 3)
                .forEach(i -> executorService.submit(new Processor1(i, countDownLatch)));

        executorService.shutdown();

        IntStream.range(0, 3)
                .forEach(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                });

        System.out.println("Latch has been opened, main thread is proceeding");
    }
}

class Processor implements Runnable {
    private CountDownLatch countDownLatch;

    public Processor(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println("Thread #" + Thread.currentThread().getId() + " count:" + countDownLatch.getCount());
    }
}

class Processor1 implements Runnable {
    private CountDownLatch countDownLatch;
    private final Integer processorId;

    public Processor1(Integer processorId, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.processorId = processorId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Id #" + processorId + " count:" + countDownLatch.getCount());
    }
}