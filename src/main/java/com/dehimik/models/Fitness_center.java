package com.dehimik.models;

import java.io.*;
import java.util.*;

public class Fitness_center {
    private List<Client> clients;
    private List<Coach> coaches;

    public Fitness_center() {
        this.clients = new ArrayList<>();
        this.coaches = new ArrayList<>();
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addCoach(Coach coach) {
        coaches.add(coach);
    }

    public String showAllClients() {
        if (clients.isEmpty()) return "There are no registrated clients.";

        StringBuilder result = new StringBuilder("\nClients list:\n");
        for (Client client : clients) {
            result.append(client.showInfo()).append("\n");
        }
        return result.toString();
    }

    public String showAllCoaches() {
        if (coaches.isEmpty()) return "There are no registrated coaches.";

        StringBuilder result = new StringBuilder("\nCoaches list:\n");
        for (Coach coach : coaches) {
            result.append(coach.showInfo()).append("\n");
        }
        return result.toString();
    }

    public String searchClient(String name) {
        for (Client client : clients) {
            if (client.getName().equalsIgnoreCase(name)) {
                return client.showInfo();
            }
        }
        return "No client.";
    }

    public void saveData(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(clients);
            out.writeObject(coaches);
            System.out.println("Data successfully saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            clients = (List<Client>) in.readObject();
            coaches = (List<Coach>) in.readObject();
            System.out.println("Data successfully loaded.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
