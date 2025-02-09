package com.dehimik.utils;

import com.dehimik.enumes.SubscriptionType;

import java.util.HashMap;
import java.util.Map;

public class FinanceManager {
    private Map<SubscriptionType, Integer> soldSubscriptions;
    private double totalRevenue;
    private double totalEquipmentExpenses;

    public FinanceManager() {
        this.soldSubscriptions = new HashMap<>();
        this.totalRevenue = 0.0;
        this.totalEquipmentExpenses = 0.0;
    }

    public void sellSubscription(SubscriptionType type, double price) {
        soldSubscriptions.put(type, soldSubscriptions.getOrDefault(type, 0) + 1);
        totalRevenue += price;
    }

    public void addEquipmentExpense(double amount) {
        totalEquipmentExpenses += amount;
    }

    public String getFinanceReport() {
        StringBuilder report = new StringBuilder("Фінансовий звіт:\n");
        report.append("Доходи від абонементів:\n");
        for (Map.Entry<SubscriptionType, Integer> entry : soldSubscriptions.entrySet()) {
            report.append(entry.getKey().getDescription())
                    .append(": ")
                    .append(entry.getValue())
                    .append(" продано\n");
        }
        report.append("Загальний дохід: ").append(totalRevenue).append(" грн\n");
        report.append("Загальні витрати на обладнання: ").append(totalEquipmentExpenses).append(" грн\n");

        return report.toString();
    }
}
