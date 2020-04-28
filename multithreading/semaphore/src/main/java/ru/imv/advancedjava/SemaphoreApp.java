package ru.imv.advancedjava;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * SemaphoreApp
 */
public class SemaphoreApp {
    public static void main(String[] args) throws InterruptedException {
        demo_3();
    }

    private static void demo_1() {
        Semaphore semaphore = new Semaphore(3);

        try {
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();

            System.out.println("All semaphore's permits have been acquired");

            semaphore.acquire();
            System.out.println("Can't reach here...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        semaphore.release();

        System.out.println(semaphore.availablePermits());
    }

    private static void demo_2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Connection connection = Connection.getConnection();

        IntStream.range(0, 10)
                .forEach(i -> {
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                connection.doWork();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

    private static void demo_3() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);

        Connection2 connection = Connection2.getConnection();

        IntStream.range(0, 200)
                .forEach(i -> {
                    executorService.submit(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                connection.work();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }

}

class Connection {
    private static Connection connection = new Connection();
    private int connectionsCount;

    private Connection() {
    }

    public static Connection getConnection() {
        return connection;
    }

    public void doWork() throws InterruptedException {
        synchronized (this) {
            System.out.println("connectionsCount : " + ++connectionsCount);
        }

        Thread.sleep(5000);

        synchronized (this) {
            connectionsCount--;
        }

    }
}

class Connection2 {
    private static Connection2 connection = new Connection2();
    private int connectionsCount;
    private Semaphore semaphore = new Semaphore(10);

    private Connection2() {
    }

    public static Connection2 getConnection() {
        return connection;
    }

    public void work() throws InterruptedException {
        semaphore.acquire();
        try {
            doWork();
        } finally {
            semaphore.release();
        }
    }

    private void doWork() throws InterruptedException {
        synchronized (this) {
            System.out.println("connectionsCount : " + ++connectionsCount);
        }

        Thread.sleep(5000);

        synchronized (this) {
            connectionsCount--;
        }

    }
}
