package ru.imv.advancedjava;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class ReflectionApp {
    public static void main(String[] args) {
        Person p;

        Class personClass = Person.class;
        Class personClass1 = (p = new Person()).getClass();
        Class personClass2 = null;
        try {
            personClass2 = Class.forName("ru.imv.advancedjava.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(personClass.equals(personClass1));
        System.out.println(personClass.equals(personClass2));

        Method[] personMethods = personClass.getMethods();
        Arrays.stream(personMethods).forEach(method -> {
            System.out.print("Method: " + method.getName() + "\t");
            System.out.print("ReturnType: " + method.getReturnType().getSimpleName() + "\t");
            System.out.print("Arguments: " +
                    Arrays.stream(method.getParameters())
                            .map(param -> param.getType().getSimpleName() + " " + param.getName())
                            .collect(Collectors.joining(", "))
                    + "\t\n");
        });

        Field[] fields = personClass1.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            System.out.print("Field: " + field.getName() + "\t");
            System.out.print("Type: " + field.getType().getSimpleName() + "\t\n");
        });

        Annotation[] annotations = personClass1.getAnnotations();
        Arrays.stream(annotations).forEach(a -> {
            System.out.println("Annotation: " + Arrays.toString(a.annotationType().getAnnotatedInterfaces()) + "\t");
        });
    }
}
