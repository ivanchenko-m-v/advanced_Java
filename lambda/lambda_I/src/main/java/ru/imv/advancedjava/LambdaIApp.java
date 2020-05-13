package ru.imv.advancedjava;

interface Executable {
    void execute();
}

class Runner {
    public void run(Executable e) {
        e.execute();
    }
}

class ExecutableImpl implements Executable {

    @Override
    public void execute() {
        System.out.println("ExecutableImpl");
    }
}

/**
 * LambdaIApp
 */
public class LambdaIApp {
    public static void main(String[] args) {
        Runner runner = new Runner();

        runner.run(new ExecutableImpl());
        runner.run(new Executable() {
            @Override
            public void execute() {
                System.out.println("Executable anonymous class");
            }
        });
        runner.run(() -> System.out.println("Executable anonymous method"));
    }
}
