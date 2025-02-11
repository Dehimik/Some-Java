package com.dehimik.enumes;

public enum Specialization {
    FITNESS("Fitness"),
    YOGA("Yoga"),
    BOXING("Boxing"),
    CROSSFIT("Crossfit"),
    BODYBUILDING("Body Building");

    private final String description;

    Specialization(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Specialization fromUserChoice(int choice) {
        Specialization[] values = Specialization.values();
        if (choice >= 1 && choice <= values.length) {
            return values[choice - 1];
        }
        return null;
    }

    public static void printOptions() {
        System.out.println("Choose coach specialization:");
        for (int i = 0; i < Specialization.values().length; i++) {
            System.out.println((i + 1) + ". " + Specialization.values()[i].getDescription());
        }
    }
}
