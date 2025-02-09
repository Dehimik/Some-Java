package com.dehimik.models;

import com.dehimik.enumes.SubscriptionType;
import com.dehimik.models.Person;

public class Client extends Person {
    private SubscriptionType subscription;

    public Client(String name, SubscriptionType subscription) {
        super(name);
        this.subscription = subscription;
    }

    public SubscriptionType getSubscription() {
        return subscription;
    }

    @Override
    public String showInfo() {
        return "Client: " + name + ", Subscription: " + subscription;
    }
}
