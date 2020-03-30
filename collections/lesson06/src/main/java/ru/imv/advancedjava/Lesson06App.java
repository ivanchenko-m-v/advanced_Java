package ru.imv.advancedjava;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Hello world!
 */
public class Lesson06App {
    public static void main(String[] args) {
        //не гарантируется порядок
        Map<Integer, String> hashMap = new HashMap<>();
        //вывод - в порядке добавления
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        //пары отсортированы по ключу
        Map<Integer, String> treeMap = new TreeMap<>();

        System.out.println("HashMap");
        testMap(hashMap);

        System.out.println("LinkedHashMap");
        testMap(linkedHashMap);

        System.out.println("TreeMap");
        testMap(treeMap);
    }

    private static void testMap(Map<Integer, String> map) {
        map.put(39, "Bob");
        map.put(10, "Alice");
        map.put(1, "Mike");
        map.put(1600, "Alex");
        map.put(97, "Ksu");
        map.put(0, "Ildar");
        map.put(8, "Zhen");

        map.keySet().stream()
                .forEach(
                        key ->
                                System.out.println(String.format("%4d : %s", key, map.get(key)))
                );
    }
}
