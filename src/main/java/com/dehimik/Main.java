package com.dehimik;

import com.dehimik.utils.InputValidator;
import com.dehimik.models.Client;
import com.dehimik.models.Coach;
import com.dehimik.models.Fitness_center;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Fitness_center fitnessCenter = new Fitness_center();
        Scanner scanner = new Scanner(System.in);
        String fileName = "fitness_data.ser";

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add  client");
            System.out.println("2. Add coach");
            System.out.println("3. Show all clients");
            System.out.println("4. Show all coaches");
            System.out.println("5. Search client");
            System.out.println("6. Save data");
            System.out.println("7. Load data");
            System.out.println("8. Exit");
            System.out.print("Your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();// Очищення буфера після nextInt()

                switch (choice) {
                    case 1:
                        System.out.print("Enter client name: ");
                        String clientName = InputValidator.cleanName(scanner.nextLine());
                        System.out.print("Enter subscription: ");
                        String subscription = scanner.nextLine();
                        fitnessCenter.addClient(new Client(clientName, subscription));
                        System.out.println("Client successfully added.");
                        break;

                    case 2:
                        System.out.print("Enter coach name: ");
                        String trainerName = InputValidator.cleanName(scanner.nextLine());
                        System.out.print("Enter coach specialization: ");
                        String specialization = scanner.nextLine();
                        fitnessCenter.addCoach(new Coach(trainerName, specialization));
                        System.out.println("Coach successfully added.");
                        break;

                    case 3:
                        System.out.println(fitnessCenter.showAllClients());
                        break;

                    case 4:
                        System.out.println(fitnessCenter.showAllCoaches());
                        break;

                    case 5:
                        System.out.print("Enter client name to search: ");
                        String searchName = scanner.nextLine();
                        System.out.println(fitnessCenter.searchClient(searchName));
                        break;

                    case 6:
                        fitnessCenter.saveData(fileName);
                        break;

                    case 7:
                        fitnessCenter.loadData(fileName);
                        break;

                    case 8:
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
}

// Need to add validator
// Clear spec symbols and spaces in name
// Add list of subscriptions and specializations
// Add registration on a phone number
