package com.dehimik.enumes;

public enum SubscriptionType {
    BASIC("Базовий"),
    STANDARD("Стандартний"),
    PREMIUM("Преміум");

    private final String description;

    SubscriptionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static SubscriptionType fromUserChoice(int choice) {
        SubscriptionType[] values = SubscriptionType.values();
        if (choice >= 1 && choice <= values.length) {
            return values[choice - 1];
        }
        return null; // Некоректний вибір
    }

    public static void printOptions() {
        System.out.println("Оберіть тип абонемента:");
        for (int i = 0; i < SubscriptionType.values().length; i++) {
            System.out.println((i + 1) + ". " + SubscriptionType.values()[i].getDescription());
        }
    }
}
