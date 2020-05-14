package ru.imv.advancedjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 */
public class Lambda2App {
    public static void main(String[] args) {
        int[] array = new int[10];
        List<Integer> list = new ArrayList<>();

        fillArray(array);
        fillList(list);

        System.out.println("array:\t" + Arrays.toString(array));
        System.out.println("list:\t" + list);

        //map
        System.out.println("--------------Method `map`--------------");
        int[] arr1 = Arrays.stream(array).map(i -> i * 2).toArray();
        List<Integer> list1 = list.stream().map(i -> i * 2).collect(Collectors.toList());

        System.out.println("array:\t" + Arrays.toString(arr1));
        System.out.println("list:\t" + list1);

        //filter
        System.out.println("--------------Method `filter`--------------");
        int[] arr2 = Arrays.stream(array).filter(i -> ((i & 1) == 0)).toArray();
        List<Integer> list2 = list.stream().filter(i -> ((i & 1) == 0)).collect(Collectors.toList());

        System.out.println("array:\t" + Arrays.toString(arr2));
        System.out.println("list:\t" + list2);

        //reduce
        System.out.println("--------------Method `reduce`--------------");
        int arrSum = Arrays.stream(array).reduce((sum, it) -> (sum + it)).getAsInt();
        int listSum = list.stream().reduce((prod, it) -> (prod * it)).get();

        System.out.println("array sum:\t" + arrSum);
        System.out.println("list prod:\t" + listSum);
    }

    private static void fillList(List<Integer> list) {
        IntStream.range(0, 10).forEach(i -> list.add(i + 1));
    }

    private static void fillArray(int[] array) {
        IntStream.range(0, 10).forEach(i -> array[i] = (i + 1));
    }
}
