package com.dehimik.models;

import com.dehimik.enumes.Specialization;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Ігнорує невідомі поля (якщо вони з’являться у JSON)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session implements Serializable {
    private Coach coach;
    private List<Client> clients = new ArrayList<>();
    private Specialization specialization;

    @JsonProperty("dateTime")
    private String dateTimeString;

    @JsonIgnore
    private LocalDateTime dateTime;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public Session() {
         this.clients = new ArrayList<>();
    }

    public Session(Coach coach, List<Client> clients, Specialization specialization, LocalDateTime dateTime) {
        this.coach = coach;
        this.clients = (clients != null) ? clients : new ArrayList<>();
        this.specialization = specialization;
        this.dateTime = dateTime;
        this.dateTimeString = dateTime.format(FORMATTER);
    }

    public Coach getCoach() {
        return coach;
    }

    public String getClientsNames() {
        if (clients == null || clients.isEmpty()) {
            return "No clients";
        }
        return clients.stream()
                .map(Client::getName)
                .collect(Collectors.joining(", "));
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return dateTime.format(formatter);
    }

    public String getFormattedDate() {
        return dateTimeString;
    }

    @JsonIgnore
    public LocalDateTime getDateTime() {
        if (dateTime == null && dateTimeString != null) {
            dateTime = LocalDateTime.parse(dateTimeString, FORMATTER);
        }
        return dateTime;
    }

    public String showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Session: ").append(specialization.getDescription()).append("\n");
        sb.append("Coach: ").append(coach.getName()).append("\n");
        sb.append("Date and time: ").append(getFormattedDateTime()).append("\n");
        sb.append("Clients: ");
        if (clients.isEmpty()) {
            sb.append("No members.\n");
        } else {
            for (Client client : clients) {
                sb.append(client.getName()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Видаляємо останню кому
            sb.append("\n");
        }
        return sb.toString();
    }
}
