package com.dehimik.models;

import com.dehimik.enumes.Specialization;
import com.dehimik.models.Person;

public class Coach extends Person {
    private Specialization specialization;

    public Coach(String name, Specialization specialization) {
        super(name);
        this.specialization = specialization;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    @Override
    public String showInfo() {
        return "Coach: " + name + ", Specialization: " + specialization;
    }
}
