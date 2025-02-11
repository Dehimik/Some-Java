package com.dehimik;

import com.dehimik.enumes.Specialization;
import com.dehimik.enumes.SubscriptionType;
import com.dehimik.utils.InputValidator;
import com.dehimik.models.Client;
import com.dehimik.models.Coach;
import com.dehimik.models.Fitness_center;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Fitness_center fitnessCenter = new Fitness_center();
        Scanner scanner = new Scanner(System.in);
        String fileName = "fitness_data.ser";

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add client");
            System.out.println("2. Add coach");
            System.out.println("3. Add session");
            System.out.println("4. Show all clients");
            System.out.println("5. Show all coaches");
            System.out.println("6. Show all sessions");
            System.out.println("7. Search client");
            System.out.println("8. Save to file");
            System.out.println("9. Load from file");
            System.out.println("10. Exit");
            System.out.print("Your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();// Очищення буфера після nextInt()

                switch (choice) {
                    case 1:
                        addClient(fitnessCenter, scanner);
                        break;

                    case 2:
                        addCoach(fitnessCenter, scanner);
                        break;

                    case 3:
                        addSession(fitnessCenter, scanner);
                        break;

                    case 4:
                        System.out.println(fitnessCenter.showAllClients());
                        break;

                    case 5:
                        System.out.println(fitnessCenter.showAllCoaches());
                        break;

                    case 6:
                        System.out.println(fitnessCenter.showAllSessions());
                        break;

                    case 7:
                        System.out.print("Enter client name to search: ");
                        String searchName = scanner.nextLine();
                        System.out.println(fitnessCenter.searchClient(searchName));
                        break;

                    case 8:
                        fitnessCenter.saveData(fileName);
                        break;

                    case 9:
                        fitnessCenter.loadData(fileName);
                        break;

                    case 10:
                        System.out.println("Exit...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Wrong choice. Try again.");
                }
            } catch(Exception e){
                System.out.println("Something wrong.");
                scanner.nextLine();
            }
        }
    }
    private static void addClient(Fitness_center fitnessCenter, Scanner scanner){
        System.out.print("Enter client name: ");
        String name = InputValidator.cleanName(scanner.nextLine());

        SubscriptionType.printOptions();
        System.out.print("Choose subscription number: ");
        int subChoice = scanner.nextInt();
        scanner.nextLine();
        SubscriptionType subscription = SubscriptionType.fromUserChoice(subChoice);

        if (subscription != null) {
            fitnessCenter.addClient(new Client(name, subscription));
            System.out.println("Client " + name + " added.");
        } else {
            System.out.println("Error: Incorrect subscription.");
        }
    }
    private static void addCoach(Fitness_center fitnessCenter, Scanner scanner){
        System.out.print("Enter coach name: ");
        String name = InputValidator.cleanName(scanner.nextLine());

        Specialization.printOptions();
        System.out.print("Choose specialization number: ");
        int specChoice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        Specialization specialization = Specialization.fromUserChoice(specChoice);

        if (specialization != null) {
            fitnessCenter.addCoach(new Coach(name, specialization));
            System.out.println("Coach " + name + " added with specialization " + specialization.getDescription());
        } else {
            System.out.println("Error: Incorrect specialization type.");
        }
    }
    private static void addSession(Fitness_center fitnessCenter, Scanner scanner){
        if (fitnessCenter.getCoaches().isEmpty() || fitnessCenter.getClients().isEmpty()) {
            System.out.println("Error: Add clients and coaches at first.");
            return;
        }

        System.out.println("Choose coach:");
        for (int i = 0; i < fitnessCenter.getCoaches().size(); i++) {
            System.out.println((i + 1) + ". " + fitnessCenter.getCoaches().get(i).getName());
        }
        System.out.print("Your choice: ");
        int coachIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        Coach coach = fitnessCenter.getCoaches().get(coachIndex);

        List<Client> sessionClients = new ArrayList<>();
        System.out.println("Choose clients (numbers with space):");
        for (int i = 0; i < fitnessCenter.getClients().size(); i++) {
            System.out.println((i + 1) + ". " + fitnessCenter.getClients().get(i).getName());
        }
        System.out.print("Your choice: ");
        String[] clientIndexes = scanner.nextLine().split(" ");
        for (String index : clientIndexes) {
            int clientIndex = Integer.parseInt(index) - 1;
            sessionClients.add(fitnessCenter.getClients().get(clientIndex));
        }

        Specialization.printOptions();
        System.out.print("Choose specialization number: ");
        int specChoice = scanner.nextInt();
        scanner.nextLine();
        Specialization specialization = Specialization.fromUserChoice(specChoice);

        System.out.print("Enter data and time in format: 'dd.MM.yyyy HH:mm': ");
        String dateTimeString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        fitnessCenter.addSession(coach, sessionClients, specialization, dateTime);
        System.out.println("Session successfully added!");
    }
}

// Need to add validator
// Clear spec symbols and spaces in name
// Add list of subscriptions and specializations
// Add registration on a phone number
