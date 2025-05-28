package com.killerexpertise.example.client;

import com.killerexpertise.example.service.impl.Achievement;

import java.util.Scanner;

public class Report {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Achievement a = new Achievement();

        try {
            while (true) {
                System.out.println("\nEnter the following Choice: ");
                System.out.println("1. insertData 2. viewSingleData 3. viewAllData");
                System.out.println("4. updateData 5. deleteSingleData 6. deleteAllData 7. Exit");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        a.insertData();
                        break;
                    case 2:
                        a.viewSingleData();
                        break;
                    case 3:
                        a.viewAllData();
                        break;
                    case 4:
                        a.updateData();
                        break;
                    case 5:
                        a.deleteSingleData();
                        break;
                    case 6:
                        a.deleteAllData();
                        break;
                    case 7:
                        System.out.println("Exiting program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice!");
                        break;
                }
            }
        } finally {
            sc.close();
        }
    }
}