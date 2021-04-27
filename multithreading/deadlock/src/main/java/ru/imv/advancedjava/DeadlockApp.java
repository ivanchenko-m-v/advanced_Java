package ru.imv.advancedjava;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class DeadlockApp {
    public static void main(String[] args) throws InterruptedException {
        main_3();
    }

    /*
    synchronized
     */
    private static void main_1() throws InterruptedException {
        Runner runner = new Runner();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }

    /*
    Lock
     */
    private static void main_2() throws InterruptedException {
        Runner1 runner = new Runner1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }

    /*
    synchrinized
     */
    private static void main_3() throws InterruptedException {
        Runner2 runner = new Runner2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }

}

class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account accOut, Account accIn, int amount) {
        accOut.withdraw(amount);
        accIn.deposit(amount);
    }
}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();
    private Random random = new Random();

    public void firstThread() {
        IntStream.range(0, 10000)
                .forEach(i -> {
                    synchronized (account1) {
                        synchronized (account2) {
                            Account.transfer(account1, account2, random.nextInt(100));
                        }
                    }
                });
    }

    public void secondThread() {
        IntStream.range(0, 10000)
                .forEach(i -> {
                    synchronized (account1) {
                        synchronized (account2) {
                            Account.transfer(account2, account1, random.nextInt(100));
                        }
                    }
                });
    }

    public void finished() {
        System.out.println("Account1 : " + account1.getBalance());
        System.out.println("Account2 : " + account2.getBalance());
        System.out.println("Total balance : " + (account1.getBalance() + account2.getBalance()));
    }
}

class Runner1 {
    private Account account1 = new Account();
    private Account account2 = new Account();

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    private Random random = new Random();

    private void takeLocks(Lock l1, Lock l2) {
        boolean firstLockTacken = false;
        boolean secondLockTacken = false;

        while (true) {
            try {
                firstLockTacken = l1.tryLock();
                secondLockTacken = l2.tryLock();
            } finally {
                if (firstLockTacken && secondLockTacken) {
                    return;
                }
                if (firstLockTacken) {
                    l1.unlock();
                }
                if (secondLockTacken) {
                    l2.unlock();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void releaseLocks(Lock l1, Lock l2) {
        l1.unlock();
        l2.unlock();
    }

    public void firstThread() {
        IntStream.range(0, 10000)
                .forEach(i -> {
                    takeLocks(lock2, lock1);
                    try {
                        Account.transfer(account1, account2, random.nextInt(100));
                    } finally {
                        releaseLocks(lock1, lock2);
                    }
                });
    }

    public void secondThread() {
        IntStream.range(0, 10000)
                .forEach(i -> {
                    takeLocks(lock1, lock2);
                    try {
                        Account.transfer(account2, account1, random.nextInt(100));
                    } finally {
                        releaseLocks(lock1, lock2);
                    }
                });
    }

    public void finished() {
        System.out.println("Account1 : " + account1.getBalance());
        System.out.println("Account2 : " + account2.getBalance());
        System.out.println("Total balance : " + (account1.getBalance() + account2.getBalance()));
    }
}

class Runner2 {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Integer int1 = 0, int2 = 0;

    private Random random = new Random();

    public void firstThread() {
        IntStream.range(0, 10000)
                .forEach(i -> {
                    synchronized (int1) {
                        synchronized (int2) {
                            Account.transfer(account1, account2, random.nextInt(100));
                        }
                    }
                });
    }

    public void secondThread() {
        IntStream.range(0, 10000)
                .forEach(i -> {
                    synchronized (int1) {
                        synchronized (int2) {
                            Account.transfer(account2, account1, random.nextInt(100));
                        }
                    }
                });
    }

    public void finished() {
        System.out.println("Account1 : " + account1.getBalance());
        System.out.println("Account2 : " + account2.getBalance());
        System.out.println("Total balance : " + (account1.getBalance() + account2.getBalance()));
    }
}