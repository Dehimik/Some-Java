package com.dehimik.models;


public class Equipment {
    private String name;
    private int price;
    private int quantity;
    private int totalCost;

    public Equipment() { }

    public Equipment(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalCost(){
        return quantity * price;
    }

    @Override
    public String toString(){
        return name + " (x" + quantity + ") - $" + price;
    }
}
