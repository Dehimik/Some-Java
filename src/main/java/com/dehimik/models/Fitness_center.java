package com.dehimik.models;

import com.dehimik.enumes.Specialization;
import com.dehimik.utils.FinanceManager;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Fitness_center {
    private List<Client> clients;
    private List<Coach> coaches;
    private List<Session> sessions;
    private FinanceManager financeManager;

    private static final String CLIENTS_FILE = "clients.json";
    private static final String COACHES_FILE = "coaches.json";
    private static final String SESSIONS_FILE = "sessions.json";

    public Fitness_center() {
        this.clients = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.financeManager = new FinanceManager();
    }

    public void addClient(Client client, double price) {
        clients.add(client);
        financeManager.sellSubscription(client.getSubscription(), price);
    }

    public void addCoach(Coach coach) {
        coaches.add(coach);
    }

    public void addSession(Coach coach, List<Client> clients, Specialization specialization, LocalDateTime dateTime) {
        sessions.add(new Session(coach, clients, specialization, dateTime));
    }

    public String showAllSessions() {
        if (sessions.isEmpty()) return "Немає запланованих занять.";

        StringBuilder result = new StringBuilder("\nСписок занять:\n");
        for (Session session : sessions) {
            result.append(session.showInfo()).append("\n");
        }
        return result.toString();
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
