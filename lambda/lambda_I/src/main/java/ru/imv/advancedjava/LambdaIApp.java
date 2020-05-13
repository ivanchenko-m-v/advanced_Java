package ru.imv.advancedjava;

interface Executable {
    int execute(int x, int y);
}

class Runner {
    public void run(Executable e) {
        System.out.println(e.execute(8, 12));
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
            public int execute(int x, int y) {
                System.out.println("Executable anonymous class");
                return x + y + 1;
            }
        });
        runner.run((x, y) -> {
            System.out.println("Executable anonymous method");
            return x + y + 5;
        });
    }
}
