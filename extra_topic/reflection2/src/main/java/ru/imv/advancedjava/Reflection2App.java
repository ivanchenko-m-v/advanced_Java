package ru.imv.advancedjava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Hello world!
 */
public class Reflection2App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Создать объект Класс1, вызвать метод с названием Метод с параметром Класс2
        Class callerClass;
        try {
            callerClass = Class.forName(scanner.next());//Класс1
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        Class paramClass;
        try {
            paramClass = Class.forName(scanner.next());//Класс2
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        String methodName = scanner.next();//Метод
        Method method;
        try {
            method = callerClass.getMethod(methodName, paramClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }
        Object callerObject;
        try {
            callerObject = callerClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        }
        Object paramObject;
        try {
            paramObject = paramClass.getConstructor(String.class).newInstance("String value");
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }
        try {
            method.invoke(callerObject, paramObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(callerObject);
    }
}
