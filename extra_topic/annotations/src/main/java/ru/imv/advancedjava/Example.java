package ru.imv.advancedjava;

public class Example {
    @MethodInfo(author = "imv", dateOfCreation = 20200517, purpose = "test")
    public void printHelloWorld(){
        System.out.println("Hello, World!");
    }

    @MethodInfo
    public void print(){
        System.out.println("Hello, Print!");
    }
}//Example
