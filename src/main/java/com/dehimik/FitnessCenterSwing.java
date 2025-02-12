package com.dehimik;
import com.dehimik.enumes.Specialization;
import com.dehimik.enumes.SubscriptionType;
import com.dehimik.models.Client;
import com.dehimik.models.Coach;
import com.dehimik.models.Fitness_center;
import com.dehimik.ui.AddSession;
import com.dehimik.utils.InputValidator;

import javax.swing.*;
import java.awt.*;

public class FitnessCenterSwing extends JFrame {
    private Fitness_center fitnessCenter;
    private JTextArea displayArea;
    private JTextField clientNameField;
    private JTextField coachNameField;
    private JTextField searchField;
    private JComboBox<SubscriptionType> subscriptionBox;
    private JComboBox<Specialization> specializationBox;
    private static final String FILE_NAME = "fitness_data.ser";

    public FitnessCenterSwing() {
        fitnessCenter = new Fitness_center();

        setTitle("Fitness center");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JButton addClientButton = new JButton("Add client");
        JButton addCoachButton = new JButton("Add coach");
        JButton addSessionButton = new JButton("Add session");
        JButton showClientsButton = new JButton("Clients list");
        JButton showCoachesButton = new JButton("Coaches list");
        JButton showSessionButton = new JButton("Sessions list");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton searchButton = new JButton("Search client");

        topPanel.add(addClientButton);
        topPanel.add(addCoachButton);
        topPanel.add(addSessionButton);
        topPanel.add(showClientsButton);
        topPanel.add(showCoachesButton);
        topPanel.add(showSessionButton);
        topPanel.add(saveButton);
        topPanel.add(loadButton);
        topPanel.add(searchButton);

        add(topPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        clientNameField = new JTextField();
        coachNameField = new JTextField();
        searchField = new JTextField();
        subscriptionBox = new JComboBox<>(SubscriptionType.values());
        specializationBox = new JComboBox<>(Specialization.values());

        inputPanel.add(new JLabel("Client name:"));
        inputPanel.add(clientNameField);
        inputPanel.add(new JLabel("Subscription:"));
        inputPanel.add(subscriptionBox);
        inputPanel.add(new JLabel("Coach name:"));
        inputPanel.add(coachNameField);
        inputPanel.add(new JLabel("Specialization:"));
        inputPanel.add(specializationBox);
        inputPanel.add(new JLabel("Search client:"));
        inputPanel.add(searchField);

        add(inputPanel, BorderLayout.SOUTH);

        addClientButton.addActionListener(e -> addClient());
        addCoachButton.addActionListener(e -> addCoach());
        addSessionButton.addActionListener(e -> new AddSession(this, fitnessCenter));
        showClientsButton.addActionListener(e -> displayArea.setText(fitnessCenter.showAllClients()));
        showCoachesButton.addActionListener(e -> displayArea.setText(fitnessCenter.showAllCoaches()));
        showSessionButton.addActionListener(e -> displayArea.setText(fitnessCenter.showAllSessions()));
        saveButton.addActionListener(e -> fitnessCenter.saveData(FILE_NAME));
        loadButton.addActionListener(e -> fitnessCenter.loadData(FILE_NAME));
        searchButton.addActionListener(e -> searchClient());
    }

    private void addClient() {
        String name = InputValidator.cleanName(clientNameField.getText());
        SubscriptionType subscription = (SubscriptionType) subscriptionBox.getSelectedItem();

        if (!name.isEmpty() && subscription != null) {
            fitnessCenter.addClient(new Client(name, subscription));
            displayArea.setText("Client " + name + " added with subscription " + subscription.getDescription() +
                    " ($" + subscription.getPrice() + ")");
            clientNameField.setText("");
            subscriptionBox.setSelectedIndex(0);
        } else {
            displayArea.setText("Error: Enter name and choose a subscription.");
        }
    }

    private void addCoach() {
        String name = InputValidator.cleanName(coachNameField.getText());
        Specialization spec = (Specialization) specializationBox.getSelectedItem();

        if(!name.isEmpty() && spec != null){
            fitnessCenter.addCoach(new Coach(name, spec));
            displayArea.setText("Coach " + name + " added with specialization " + spec.getDescription());
            coachNameField.setText("");
            specializationBox.setSelectedIndex(0);
        } else {
            displayArea.setText("Error: Enter name and choose a specialization.");
        }
    }

    private void addSession(){

    }

    private void searchClient() {
        String name = searchField.getText();
        if (!name.isEmpty()) {
            displayArea.setText(fitnessCenter.searchClient(name));
        } else {
            displayArea.setText("Error: Enter name for search.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FitnessCenterSwing().setVisible(true));
    }
}
