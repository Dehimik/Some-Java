package com.dehimik;

class Client extends Person{
    private String subscription;

    public Client(String name, String subscription) {
        super(name);
        this.subscription = subscription;
    }

    public String getSubscription() {
        return subscription;
    }

    @Override
    public String showInfo() {
        return "Client: " + name + ", Subscription: " + subscription;
    }
}
