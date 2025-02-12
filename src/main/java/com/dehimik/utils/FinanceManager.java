package com.dehimik.utils;

import com.dehimik.enumes.SubscriptionType;
import com.dehimik.models.Equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinanceManager {
    private List<Equipment> equipmentList;
    private Map<SubscriptionType, Integer> soldSubscriptions;
    private int totalRevenue;
    private int totalExpenses;

    public FinanceManager() {
        this.soldSubscriptions = new HashMap<>();
        this.equipmentList = new ArrayList<>();
        this.totalRevenue = 0;
        this.totalExpenses = 0;
    }

    public void sellSubscription(SubscriptionType type) {
        soldSubscriptions.put(type, soldSubscriptions.getOrDefault(type, 0) + 1);
        totalRevenue += type.getPrice();
    }

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
        totalExpenses += equipment.getTotalCost();
    }

    public int getNetProfit() {
        return totalRevenue - totalExpenses;
    }

    public String getFinanceReport() {
        StringBuilder report = new StringBuilder("Finance report:\n");

        report.append("\nIncomes from clients:\n");
        for (Map.Entry<SubscriptionType, Integer> entry : soldSubscriptions.entrySet()) {
            report.append(entry.getKey().getDescription())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" Sold ($" + (entry.getValue() * entry.getKey().getPrice()) + ")\n");
        }

        report.append("\nEquipment costs:\n");
        for (Equipment e : equipmentList) {
            report.append("- ").append(e).append("\n");
        }

        report.append("\nTotal income: $").append(totalRevenue).append("\n");
        report.append("\nTotal expenses: $").append(totalExpenses).append("\n");
        report.append("\nNet profit: $").append(getNetProfit()).append("\n");

        return report.toString();
    }
}
