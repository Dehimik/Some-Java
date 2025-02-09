package com.dehimik.models;

import com.dehimik.enumes.Specialization;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Session implements Serializable {
    private Coach coach;
    private List<Client> clients;
    private Specialization specialization;
    private LocalDateTime dateTime;

    public Session(Coach coach, List<Client> clients, Specialization specialization, LocalDateTime dateTime) {
        this.coach = coach;
        this.clients = clients;
        this.specialization = specialization;
        this.dateTime = dateTime;
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return dateTime.format(formatter);
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
