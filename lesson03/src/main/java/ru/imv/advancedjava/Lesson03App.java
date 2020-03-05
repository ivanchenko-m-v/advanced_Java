package ru.imv.advancedjava;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class Lesson03App {
    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        List<Integer> arrayList = new ArrayList<>();

        measureTime(linkedList);
        measureTime(arrayList);
    }

    private static void measureTime(List<Integer> list) {
        long start = System.currentTimeMillis();
        IntStream.range(0, 100000).forEach(l -> list.add(0, l));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
