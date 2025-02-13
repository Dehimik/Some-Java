package com.dehimik.ui;

import com.dehimik.models.Fitness_center;
import com.dehimik.models.Session;
import com.dehimik.models.Coach;
import com.dehimik.models.Client;
import com.dehimik.enumes.Specialization;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddSession extends JDialog {
    private Fitness_center fitnessCenter;
    private JTable sessionTable;
    private DefaultTableModel tableModel;

    public AddSession(JFrame parent, Fitness_center fitnessCenter) {
        super(parent, "Session Manager", true);
        this.fitnessCenter = fitnessCenter;
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Створюємо таблицю для занять
        tableModel = new DefaultTableModel(new Object[]{"Coach", "Clients", "Specialization", "Date"}, 0);
        sessionTable = new JTable(tableModel);
        updateTable();

        // Додаємо таблицю у вікно
        add(new JScrollPane(sessionTable), BorderLayout.CENTER);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();
        JButton addSessionButton = new JButton("Add Session");
        JButton saveSessionButton = new JButton("Save sessions");
        JButton loadSessionButton = new JButton("Load sessions");
        JButton refreshButton = new JButton("Refresh List");

        buttonPanel.add(addSessionButton);
        buttonPanel.add(saveSessionButton);
        buttonPanel.add(loadSessionButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addSessionButton.addActionListener(e -> addSessionDialog());

        saveSessionButton.addActionListener(e -> fitnessCenter.saveSessions());
        loadSessionButton.addActionListener(e -> fitnessCenter.loadSessions());

        refreshButton.addActionListener(e -> updateTable());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addSessionDialog() {
        JComboBox<Coach> coachBox = new JComboBox<>(fitnessCenter.getCoaches().toArray(new Coach[0]));
        JList<Client> clientList = new JList<>(fitnessCenter.getClients().toArray(new Client[0]));
        clientList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane clientScrollPane = new JScrollPane(clientList);
        JComboBox<Specialization> specializationBox = new JComboBox<>(Specialization.values());
        JTextField dateTimeField = new JTextField("dd.MM.yyyy HH:mm");

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Choose coach:"));
        panel.add(coachBox);
        panel.add(new JLabel("Choose clients:"));
        panel.add(clientScrollPane);
        panel.add(new JLabel("Choose specialization:"));
        panel.add(specializationBox);
        panel.add(new JLabel("Enter date and time:"));
        panel.add(dateTimeField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add session", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                Coach selectedCoach = (Coach) coachBox.getSelectedItem();
                List<Client> selectedClients = clientList.getSelectedValuesList();
                Specialization specialization = (Specialization) specializationBox.getSelectedItem();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeField.getText(), formatter);

                fitnessCenter.addSession(new Session(selectedCoach, selectedClients, specialization, dateTime));
                updateTable();
                JOptionPane.showMessageDialog(this, "Session successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: Invalid date format. Use 'dd.MM.yyyy HH:mm'.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Session session : fitnessCenter.getSessions()) {
            tableModel.addRow(new Object[]{
                    session.getCoach().getName(),
                    session.getClientsNames(),
                    session.getSpecialization().getDescription(),
                    session.getFormattedDate()
            });
        }
    }
}
