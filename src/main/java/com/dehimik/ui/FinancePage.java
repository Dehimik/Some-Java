package com.dehimik.ui;

import com.dehimik.models.Fitness_center;

import javax.swing.*;
import java.awt.*;

public class FinancePage extends JDialog {
    private Fitness_center fitnessCenter;
    private JTextArea displayArea;

    public FinancePage(JFrame parent, Fitness_center fitnessCenter) {
        super(parent, "Finance report", true);
        this.fitnessCenter = fitnessCenter;
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        updateFinanceReport();

        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Update");

        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> updateFinanceReport());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateFinanceReport() {
        String report = fitnessCenter.showFinanceReport();
        displayArea.setText(report);
    }
}
