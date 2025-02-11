package com.dehimik;
import com.dehimik.models.Client;
import com.dehimik.models.Coach;
import com.dehimik.models.Fitness_center;

import javax.swing.*;
import java.awt.*;

public class FitnessCenterSwing extends JFrame {
    private Fitness_center fitnessCenter;
    private JTextArea displayArea;
    private JTextField clientNameField, clientSubscriptionField;
    private JTextField coachNameField, coachSpecializationField;
    private JTextField searchField;
    private static final String FILE_NAME = "fitness_data.ser";

    public FitnessCenterSwing() {
        fitnessCenter = new Fitness_center();

        setTitle("Fitness center");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JButton addClientButton = new JButton("Add client");
        JButton addCoachButton = new JButton("Add coach");
        JButton showClientsButton = new JButton("Clients list");
        JButton showCoachesButton = new JButton("Coaches list");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton searchButton = new JButton("Search client");

        topPanel.add(addClientButton);
        topPanel.add(addCoachButton);
        topPanel.add(showClientsButton);
        topPanel.add(showCoachesButton);
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
        clientSubscriptionField = new JTextField();
        coachNameField = new JTextField();
        coachSpecializationField = new JTextField();
        searchField = new JTextField();

        inputPanel.add(new JLabel("Client name:"));
        inputPanel.add(clientNameField);
        inputPanel.add(new JLabel("Subscription:"));
        inputPanel.add(clientSubscriptionField);
        inputPanel.add(new JLabel("Coach name:"));
        inputPanel.add(coachNameField);
        inputPanel.add(new JLabel("Specialization:"));
        inputPanel.add(coachSpecializationField);
        inputPanel.add(new JLabel("Search client:"));
        inputPanel.add(searchField);

        add(inputPanel, BorderLayout.SOUTH);

        addClientButton.addActionListener(e -> addClient());
        addCoachButton.addActionListener(e -> addCoach());
        showClientsButton.addActionListener(e -> displayArea.setText(fitnessCenter.showAllClients()));
        showCoachesButton.addActionListener(e -> displayArea.setText(fitnessCenter.showAllCoaches()));
        saveButton.addActionListener(e -> fitnessCenter.saveData(FILE_NAME));
        loadButton.addActionListener(e -> fitnessCenter.loadData(FILE_NAME));
        searchButton.addActionListener(e -> searchClient());
    }

    private void addClient() {
        //String name = clientNameField.getText().trim().replaceAll("\\s+", " ");
       // String subscription = clientSubscriptionField.getText();
        //if (!name.isEmpty() && !subscription.isEmpty()) {
        //    fitnessCenter.addClient(new Client(name, subscription), price);
        //    displayArea.setText("Client  " + name + " added.");
        //    clientNameField.setText("");
        //    clientSubscriptionField.setText("");
       // } else {
       //     displayArea.setText("Error: Enter name and subscription.");
      //  }
    }

    private void addCoach() {
       // String name = coachNameField.getText().trim().replaceAll("\\s+", " ");
        //String specialization = coachSpecializationField.getText();
       // if (!name.isEmpty() && !specialization.isEmpty()) {
         //   fitnessCenter.addCoach(new Coach(name, specialization));
         //  displayArea.setText("Coach " + name + " added.");
          //  coachNameField.setText("");
          //  coachSpecializationField.setText("");
      //  } else {
       //     displayArea.setText("Error: Enter name and specialization.");
//      }
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
