package com.dehimik;

class Coach extends Person{
    private String specialization;

    public Coach(String name, String specialization) {
        super(name);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String showInfo() {
        return "Coach: " + name + ", Specialization: " + specialization;
    }
}
