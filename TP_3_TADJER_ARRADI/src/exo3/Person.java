package exo3;

import java.util.List;

public class Person {
    String name;
    Gender gender;
    List<Person> children;

    public Person(String name, Gender gender, List<Person> children) {
        this.name = name;
        this.gender = gender;
        this.children = children;
    }
}
