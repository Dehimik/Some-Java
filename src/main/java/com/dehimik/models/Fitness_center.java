package com.dehimik.models;

import com.dehimik.enumes.*;
import com.dehimik.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
    private static final String EQUIPMENTS_FILE = "sessions.json";
    private static final String FINANCE_FILE = "sessions.json";

    public Fitness_center() {
        this.clients = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.financeManager = new FinanceManager();
    }

    public void addClient(Client client) {
        clients.add(client);
        financeManager.sellSubscription(client.getSubscription());
    }

    public void addCoach(Coach coach) {
        coaches.add(coach);
    }

    public void addSession(Coach coach, List<Client> clients, Specialization specialization, LocalDateTime dateTime) {
        sessions.add(new Session(coach, clients, specialization, dateTime));
    }

    public void addEquipment(String name, int price, int quantity) {
        Equipment equipment = new Equipment(name, price, quantity);
        financeManager.addEquipment(equipment);
    }

    public void addSession(Session session){
        sessions.add(session);
    }

    public List<Client> getClients(){
        return clients;
    }

    public List<Coach> getCoaches(){
        return coaches;
    }

    public String showAllSessions() {
        if (sessions.isEmpty()) return "There are no sessions.";

        StringBuilder result = new StringBuilder("\nSessions list:\n");
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

    public String showFinanceReport() {
        return financeManager.getFinanceReport();
    }

    public void saveClients() {
        saveToJson(CLIENTS_FILE, clients);
    }

    public void loadClients(){
        clients = loadFromJson(CLIENTS_FILE, Client.class);
        for(Client client : clients){
            financeManager.sellSubscription(client.getSubscription());
        }
    }

    public void saveCoaches(){
        saveToJson(COACHES_FILE, coaches);
    }

    public void loadCoaches(){
        coaches = loadFromJson(COACHES_FILE, Coach.class);
    }

    public void saveSessions() {
        saveToJson(SESSIONS_FILE, sessions);
    }

    public void loadSessions() {
        sessions = loadFromJson(SESSIONS_FILE, Session.class);
    }

    private <T> void saveToJson(String fileName, List<T> list) {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(new File(fileName), list);
            System.out.println("Session saved to file:  " + fileName);
        } catch (IOException e) {
            System.err.println("Error with save to file: " + fileName + ": " + e.getMessage());
        }
    }
    private <T> List<T> loadFromJson(String fileName, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            System.err.println("Error to load file: " + fileName + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
