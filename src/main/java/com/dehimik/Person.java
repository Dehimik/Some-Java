package com.dehimik;

import java.io.Serializable;

abstract class Person implements Serializable {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String showInfo();
}