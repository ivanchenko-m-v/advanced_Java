package ru.imv.advancedjava;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Vector2DTest {
    private static final double EPS = 1e-9;
    private Vector2D testVector;

    @Before
    public void createTestVector() {
        testVector = new Vector2D();
    }

    @Test
    public void newVectorShouldHaveZeroLengthAndXY() {
        Assert.assertEquals(0.0, testVector.length(), EPS);
        Assert.assertEquals(0.0, testVector.getX(), EPS);
        Assert.assertEquals(0.0, testVector.getY(), EPS);
    }

    @Test
    public void vectorShouldHaveLength() {
        testVector.setX(2);
        testVector.setY(2);

        Assert.assertEquals(2.828427125, testVector.length(), 1e-9);
    }

}