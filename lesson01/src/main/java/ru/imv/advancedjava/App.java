package ru.imv.advancedjava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> list.add(i));
        list.stream().forEach(integer -> System.out.println(integer));
    }
}
