package ru.imv.advancedjava;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {

    @Test(expected = ArithmeticException.class)
    public void testZeroDivisor() {
        MyMath.div(1, 0);
    }

}