package ru.imv.advancedjava;

public class MyMath {
    public static double div(int dividend, int divisor) {
        if (divisor == 0) throw new ArithmeticException("Can't divide by zero");

        return dividend / divisor;
    }
}//MyMath
