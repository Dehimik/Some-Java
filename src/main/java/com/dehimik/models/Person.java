package com.dehimik.models;

import java.io.Serializable;

abstract class Person implements Serializable {
    protected String name;

    public Person() { }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String showInfo();
}