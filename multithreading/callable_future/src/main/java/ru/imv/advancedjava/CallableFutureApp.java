package ru.imv.advancedjava;

import java.util.Random;
import java.util.concurrent.*;

/**
 * CallableFutureApp
 */
public class CallableFutureApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(
                new Callable<Integer>() {
                    Random random = new Random();

                    @Override
                    public Integer call() throws Exception {
                        System.out.println("Starting");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Finished");

                        int res = random.nextInt(10);
                        if (res > 5) {
                            throw new Exception("Something bad happened");
                        }
                        return res;
                    }
                });
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            int result = future.get();
            System.out.println("result is " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }
    }

    public static int calc() {
        return 5 + 4;
    }
}
