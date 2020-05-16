package ru.imv.advancedjava;

import org.junit.Assert;
import org.junit.Test;

public class Vector2DTest {

    @Test
    public void newVectorShouldHaveZeroLengthAndXY() {
        Vector2D v = new Vector2D();

        Assert.assertEquals(0.0, v.length(), 1e-9);
        Assert.assertEquals(0.0, v.getX(), 1e-9);
        Assert.assertEquals(0.0, v.getY(), 1e-9);
    }

    @Test
    public void vectorShouldHaveLength() {
        Vector2D v = new Vector2D();
        v.setX(2);
        v.setY(2);

        Assert.assertEquals(2.828427125, v.length(), 1e-9);
    }

}