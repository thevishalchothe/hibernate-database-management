package com.killerexpertise.bank.management.example;

import com.killerexpertise.bank.management.example.service.Rbi;
import com.killerexpertise.bank.management.example.service.impl.Sbi;

import java.util.Scanner;


public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Rbi bank = new Sbi();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Display All Details");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdrawal");
            System.out.println("5. Balance Check");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> bank.createAccount();
                case 2 -> bank.displayAllDetails();
                case 3 -> bank.depositeMoney();
                case 4 -> bank.withdrawal();
                case 5 -> bank.balanceCheck();
                case 6 -> {
                    System.out.println("Thank you for using our service!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice.");
            }
        }
    }
}
