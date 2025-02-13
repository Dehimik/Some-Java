package com.dehimik;

import com.dehimik.enumes.SubscriptionType;
import com.dehimik.models.Client;
import com.dehimik.models.Fitness_center;
import com.dehimik.ui.AddSession;
import com.dehimik.ui.CoachPage;
import com.dehimik.ui.EquipmentPage;
import com.dehimik.ui.FinancePage;
import com.dehimik.utils.InputValidator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FitnessCenterSwing extends JFrame {
    private Fitness_center fitnessCenter;
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JTextField clientNameField;
    private JTextField searchField;
    private JComboBox<SubscriptionType> subscriptionBox;

    public FitnessCenterSwing() {
        fitnessCenter = new Fitness_center();

        setTitle("Fitness center");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JButton addCoachButton = new JButton("Coaches");
        JButton sessionButton = new JButton("Sessions");
        JButton equipmentButton = new JButton("Equipment");
        JButton financeReport = new JButton("Finance report");

        topPanel.add(addCoachButton);
        topPanel.add(sessionButton);
        topPanel.add(equipmentButton);
        topPanel.add(financeReport);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Name", "Subscription", "Price"}, 0);
        clientTable = new JTable(tableModel);
        add(new JScrollPane(clientTable), BorderLayout.CENTER);
        updateTable();

        clientNameField = new JTextField();
        searchField = new JTextField();
        subscriptionBox = new JComboBox<>(SubscriptionType.values());

        JButton addClientButton = new JButton("Add client");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton searchButton = new JButton("Search client");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        clientNameField = new JTextField(15);
        searchField = new JTextField(15);
        subscriptionBox = new JComboBox<>(SubscriptionType.values());

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Client Name:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(clientNameField, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Subscription:"), gbc);
        gbc.gridx = 3;
        inputPanel.add(subscriptionBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Search Client:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(searchField, gbc);

        // **Кнопки "Add Client" і "Save" в одній панелі**
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addClientButton);
        buttonPanel.add(saveButton);

        gbc.gridx = 2; gbc.gridy = 1; gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        // **Кнопки "Load" і "Search Client" в одній панелі**
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel2.add(loadButton);
        buttonPanel2.add(searchButton);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4;
        inputPanel.add(buttonPanel2, gbc);

        add(inputPanel, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.SOUTH);

        addClientButton.addActionListener(e -> addClient());
        addCoachButton.addActionListener(e -> new CoachPage(this, fitnessCenter));
        sessionButton.addActionListener(e -> new AddSession(this, fitnessCenter));
        equipmentButton.addActionListener(e -> new EquipmentPage(this, fitnessCenter));
        saveButton.addActionListener(e -> fitnessCenter.saveClients());
        loadButton.addActionListener(e -> {
            fitnessCenter.loadClients();
            updateTable();
        });
        financeReport.addActionListener(e -> new FinancePage(this, fitnessCenter));
        searchButton.addActionListener(e -> searchClient());
    }

    private void addClient() {
        String name = InputValidator.cleanName(clientNameField.getText());
        SubscriptionType subscription = (SubscriptionType) subscriptionBox.getSelectedItem();

        if (!name.isEmpty() && subscription != null) {
            fitnessCenter.addClient(new Client(name, subscription));
            updateTable();
            clientNameField.setText("");
            subscriptionBox.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "Error: Enter name and choose a subscription.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchClient() {
        String name = searchField.getText();
        if (!name.isEmpty()) {
            JOptionPane.showMessageDialog(this, fitnessCenter.searchClient(name), "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error: Enter name for search.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Client client : fitnessCenter.getClients()) {
            tableModel.addRow(new Object[]{client.getName(), client.getSubscription().getDescription(), client.getSubscription().getPrice()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FitnessCenterSwing().setVisible(true));
    }
}
