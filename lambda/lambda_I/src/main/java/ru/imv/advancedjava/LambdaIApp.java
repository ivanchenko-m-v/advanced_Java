package ru.imv.advancedjava;

interface Executable {
    int execute();
}

class Runner {
    public void run(Executable e) {
        System.out.println(e.execute());
    }
}

/**
 * LambdaIApp
 */
public class LambdaIApp {
    public static void main(String[] args) {
        Runner runner = new Runner();

        runner.run(new Executable() {
            @Override
            public int execute() {
                System.out.println("Executable anonymous class");
                return 1;
            }
        });
        runner.run(() -> {
            System.out.println("Executable anonymous method");
            return 5;
        });
    }
}
