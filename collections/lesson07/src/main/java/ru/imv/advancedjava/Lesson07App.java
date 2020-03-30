package ru.imv.advancedjava;

import java.util.*;

/**
 * Hello world!
 */
public class Lesson07App {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();

        System.out.println("HashSet");
        System.out.println("------------------------------------------------");
        testSet(hashSet);
        System.out.println("````````````````````````````````````````````````");

        System.out.println("LinkedHashSet");
        System.out.println("------------------------------------------------");
        testSet(linkedHashSet);
        System.out.println("````````````````````````````````````````````````");

        System.out.println("TreeSet");
        System.out.println("------------------------------------------------");
        testSet(treeSet);
        System.out.println("````````````````````````````````````````````````");
    }

    private static void testSet(Set<String> set) {
        set.add("Bob");
        set.add("Alice");
        set.add("Mike");
        set.add("Alex");
        set.add("Ksu");
        set.add("Ildar");
        set.add("Zhen");

        set.stream()
                .forEach(
                        key ->
                                System.out.println(key)
                );
    }
}
