package de.swingbe.rest_api.model;

public class Person {
    private final String name;
    private final String about;
    private final int birthYear;

    public Person(String name, String about, int birthYear) {
        this.name = name;
        this.about = about;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public int getBirthYear() {
        return birthYear;
    }
}
