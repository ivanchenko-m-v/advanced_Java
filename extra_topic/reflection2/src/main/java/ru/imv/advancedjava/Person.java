package ru.imv.advancedjava;

@TypeInfo(author = "Michael", dateOfCreation = 20200604, purpose = "Reflection lesson")
public class Person {
    private int id;
    private String name;

    public Person() {
        id = -1;
        name = "Nothing";
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}//Person
