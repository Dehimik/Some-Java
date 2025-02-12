package com.dehimik.ui;

import com.dehimik.models.Equipment;
import com.dehimik.models.Fitness_center;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipmentPage extends JDialog {
    private Fitness_center fitnessCenter;
    private JTable equipmentTable;
    private DefaultTableModel tableModel;

    public EquipmentPage(JFrame parent, Fitness_center fitnessCenter) {
        super(parent, "Equipment manager", true);
        this.fitnessCenter = fitnessCenter;
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Створюємо модель для таблиці
        tableModel = new DefaultTableModel(new Object[]{"Name", "Price", "Quantity", "Total cost"}, 0);
        equipmentTable = new JTable(tableModel);
        updateTable();

        // Додаємо таблицю в вікно
        add(new JScrollPane(equipmentTable), BorderLayout.CENTER);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();
        JButton addEquipmentButton = new JButton("Add equipment");
        JButton saveEquipmentButton = new JButton("Save to file");
        JButton loadEquipmentButton = new JButton("Load from file");
        JButton refreshButton = new JButton("Refresh list");

        buttonPanel.add(addEquipmentButton);
        buttonPanel.add(saveEquipmentButton);
        buttonPanel.add(loadEquipmentButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEquipmentDialog();
            }
        });

        refreshButton.addActionListener(e -> updateTable());

        saveEquipmentButton.addActionListener(e -> fitnessCenter.saveEquipment());
        loadEquipmentButton.addActionListener(e -> fitnessCenter.loadEquipment());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addEquipmentDialog() {
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add equipment", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                int price = Integer.parseInt(priceField.getText().trim());
                int quantity = Integer.parseInt(quantityField.getText().trim());

                if (name.isEmpty() || price <= 0 || quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Error: All fields must be filled and price with quantity positive.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                fitnessCenter.addEquipment(name, price, quantity);
                updateTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Invalid format!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Equipment e : fitnessCenter.getEquipmentList()) {
            tableModel.addRow(new Object[]{e.getName(), e.getPrice(), e.getQuantity(), e.getTotalCost()});
        }
    }
}

