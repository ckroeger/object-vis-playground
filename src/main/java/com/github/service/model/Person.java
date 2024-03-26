package com.github.service.model;

public class Person {

    private String name;
    private int age;
    private String address;
    private String city;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
