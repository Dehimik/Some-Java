package com.dehimik.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.dehimik.models.*;
import com.dehimik.enumes.*;

public class AddSession extends JDialog {
    private JComboBox<Coach>coachBox;
    private JList<Client> clientList;
    private JComboBox<Specialization> specializationBox;
    private JTextField dateTimeField;
    private JButton addButton, cancelButton;
    private Fitness_center fitnessCenter;

    public AddSession(JFrame parent, Fitness_center fitnessCenter) {
        super(parent, "Add session", true);
        this.fitnessCenter = fitnessCenter;

        setSize(400, 200);
        setLayout(new GridLayout(5, 2));

        coachBox = new JComboBox<>(fitnessCenter.getCoaches().toArray(new Coach[0]));

        clientList = new JList<>(fitnessCenter.getClients().toArray(new Client[0]));
        clientList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane clientScrollPane = new JScrollPane(clientList);

        specializationBox = new JComboBox<>(Specialization.values());

        dateTimeField = new JTextField("dd.MM.yyyy HH:mm");

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        add(new JLabel("Choose coach:"));
        add(coachBox);
        add(new JLabel("Choose clients:"));
        add(clientScrollPane);
        add(new JLabel("Choose specialization:"));
        add(specializationBox);
        add(new JLabel("Enter date and time:"));
        add(dateTimeField);
        add(addButton);
        add(cancelButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSession();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void addSession() {
        Coach selectedCoach = (Coach) coachBox.getSelectedItem();
        List<Client> selectedClients = clientList.getSelectedValuesList();
        Specialization specialization = (Specialization) specializationBox.getSelectedItem();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeField.getText(), formatter);

            fitnessCenter.addSession(new Session(selectedCoach, selectedClients, specialization, dateTime));
            JOptionPane.showMessageDialog(this, "Session successfully added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Закриваємо діалогове вікно після успішного додавання
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error with date format. Use format 'dd.MM.yyyy HH:mm'.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

