package ru.imv.advancedjava;

import java.util.HashMap;
import java.util.Map;

/**
 * Map, HashMap
 */
public class Lesson05App {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");

        System.out.println(map);

        map.put(3, "Three (second once)");
        System.out.println(map);

        map.remove(3);
        System.out.println(map);
    }
}
