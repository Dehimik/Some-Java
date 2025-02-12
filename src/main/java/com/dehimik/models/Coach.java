package com.dehimik.models;

import com.dehimik.enumes.Specialization;
import com.dehimik.models.Person;

public class Coach extends Person {
    private Specialization specialization;

    public Coach() { }

    public Coach(String name, Specialization specialization) {
        super(name);
        this.specialization = specialization;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public String getName(){
        return name;
    }

    @Override
    public String showInfo() {
        return "Coach: " + name + ", Specialization: " + specialization;
    }

    @Override
    public String toString() {
        return name + " (" + specialization.getDescription() + ")";
    }
}
