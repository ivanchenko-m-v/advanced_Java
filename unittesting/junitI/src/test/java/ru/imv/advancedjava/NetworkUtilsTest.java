package ru.imv.advancedjava;

import org.junit.Test;

import static org.junit.Assert.*;

public class NetworkUtilsTest {

    @Test(timeout = 1200)
    public void testConnectToServer(){
        NetworkUtils.connect();
    }

}