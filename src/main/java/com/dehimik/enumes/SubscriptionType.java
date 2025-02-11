package com.dehimik.enumes;

public enum SubscriptionType {
    BASIC("Basic", 20),
    STANDARD("Standard", 30),
    PREMIUM("Premium", 150);

    private final String description;
    private final int price;

    SubscriptionType(String description, int price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public static SubscriptionType fromUserChoice(int choice) {
        SubscriptionType[] values = SubscriptionType.values();
        if (choice >= 1 && choice <= values.length) {
            return values[choice - 1];
        }
        return null;
    }

    public static void printOptions() {
        System.out.println("Choose subscription type:");
        for (int i = 0; i < SubscriptionType.values().length; i++) {
            System.out.println((i + 1) + ". " + SubscriptionType.values()[i].getDescription() + "-$ " + SubscriptionType.values()[i].getPrice());
        }
    }
}
