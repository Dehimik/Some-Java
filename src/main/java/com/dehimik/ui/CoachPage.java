package com.dehimik.ui;

import com.dehimik.models.Coach;
import com.dehimik.models.Fitness_center;
import com.dehimik.enumes.Specialization;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CoachPage extends JDialog {
    private Fitness_center fitnessCenter;
    private JTable coachTable;
    private DefaultTableModel tableModel;

    public CoachPage(JFrame parent, Fitness_center fitnessCenter) {
        super(parent, "Coach Manager", true);
        this.fitnessCenter = fitnessCenter;
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Таблиця тренерів
        tableModel = new DefaultTableModel(new Object[]{"Name", "Specialization"}, 0);
        coachTable = new JTable(tableModel);
        updateTable();

        add(new JScrollPane(coachTable), BorderLayout.CENTER);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();
        JButton addCoachButton = new JButton("Add coach");
        JButton saveCoaches = new JButton("Save coaches");
        JButton loadCoaches = new JButton("Load coaches");
        JButton refreshButton = new JButton("Refresh list");

        buttonPanel.add(addCoachButton);
        buttonPanel.add(saveCoaches);
        buttonPanel.add(loadCoaches);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addCoachButton.addActionListener(e -> addCoachDialog());

        saveCoaches.addActionListener(e -> fitnessCenter.saveCoaches());
        loadCoaches.addActionListener(e -> fitnessCenter.loadCoaches());

        refreshButton.addActionListener(e -> updateTable());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addCoachDialog() {
        JTextField nameField = new JTextField();
        JComboBox<Specialization> specializationBox = new JComboBox<>(Specialization.values());

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Specialization:"));
        panel.add(specializationBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add coach", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                Specialization specialization = (Specialization) specializationBox.getSelectedItem();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Error: Name cant be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                fitnessCenter.addCoach(new Coach(name, specialization));
                updateTable();
                JOptionPane.showMessageDialog(this, "Coach successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error with add coach!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        List<Coach> coaches = fitnessCenter.getCoaches();
        for (Coach coach : coaches) {
            tableModel.addRow(new Object[]{coach.getName(), coach.getSpecialization().getDescription()});
        }
    }
}
