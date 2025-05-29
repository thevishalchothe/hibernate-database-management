package com.killerexpertise.bank.management.example.service.impl;

import java.util.List;
import java.util.Scanner;

import com.killerexpertise.bank.management.example.model.Account;
import com.killerexpertise.bank.management.example.service.Rbi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.config.HibernateUtil;

public class Sbi implements Rbi {
    Scanner sc = new Scanner(System.in);

    @Override
    public void createAccount() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Account account = new Account();

        try {
            System.out.print("Enter 8-digit Account Number: ");
            int accNo = sc.nextInt();
            if (String.valueOf(accNo).length() != 8) {
                System.out.println("Account number must be 8 digits.");
                return;
            }

            System.out.print("Enter Full Name: ");
            sc.nextLine(); // consume newline
            String name = sc.nextLine();
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Invalid name.");
                return;
            }

            System.out.print("Enter 10-digit Mobile No: ");
            String mob = sc.next();
            if (!mob.matches("\\d{10}")) {
                System.out.println("Invalid mobile number.");
                return;
            }

            System.out.print("Enter 12-digit Aadhar No: ");
            String aadhar = sc.next();
            if (!aadhar.matches("\\d{12}")) {
                System.out.println("Invalid Aadhar number.");
                return;
            }

            System.out.print("Select Gender (1.Male 2.Female 3.Other): ");
            int genderInput = sc.nextInt();
            String gender;
            switch (genderInput) {
                case 1:
                    gender = "Male";
                    break;
                case 2:
                    gender = "Female";
                    break;
                case 3:
                    gender = "Other";
                    break;
                default:
                    System.out.println("Invalid gender input.");
                    return;
            }

            System.out.print("Enter Age (>=18): ");
            int age = sc.nextInt();
            if (age < 18) {
                System.out.println("Age must be 18 or above.");
                return;
            }

            System.out.print("Enter Initial Balance (>=1000): ");
            double balance = sc.nextDouble();
            if (balance < 1000) {
                System.out.println("Minimum balance should be 1000.");
                return;
            }

            account.setAccNo(accNo);
            account.setName(name);
            account.setMobNo(mob);
            account.setAdharNo(aadhar);
            account.setGender(gender);
            account.setAge(age);
            account.setBalance(balance);

            session.save(account);
            tx.commit();
            System.out.println("Account created successfully!");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void displayAllDetails() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> list = session.createQuery("from Account", Account.class).list();
        list.forEach(acc -> System.out.println(acc.getAccNo() + " " + acc.getName() + " " + acc.getMobNo() + " " + acc.getAdharNo() + " " + acc.getGender() + " " + acc.getAge() + " ₹" + acc.getBalance()));
        session.close();
    }

    @Override
    public void depositeMoney() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        Account account = session.get(Account.class, accNo);

        if (account != null) {
            System.out.print("Enter Amount to Deposit: ");
            double amt = sc.nextDouble();
            if (amt > 0) {
                account.setBalance(account.getBalance() + amt);
                Transaction tx = session.beginTransaction();
                session.update(account);
                tx.commit();
                System.out.println("Deposited Successfully. New Balance: ₹" + account.getBalance());
            } else {
                System.out.println("Amount must be positive.");
            }
        } else {
            System.out.println("Account not found.");
        }
        session.close();
    }

    @Override
    public void withdrawal() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        Account account = session.get(Account.class, accNo);

        if (account != null) {
            System.out.print("Enter Amount to Withdraw: ");
            double amt = sc.nextDouble();
            if (amt > 0 && account.getBalance() - amt >= 1000) {
                account.setBalance(account.getBalance() - amt);
                Transaction tx = session.beginTransaction();
                session.update(account);
                tx.commit();
                System.out.println("Withdrawal Successful. New Balance: ₹" + account.getBalance());
            } else {
                System.out.println("Insufficient funds or invalid amount.");
            }
        } else {
            System.out.println("Account not found.");
        }
        session.close();
    }

    @Override
    public void balanceCheck() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.print("Enter Account No: ");
        int accNo = sc.nextInt();
        Account account = session.get(Account.class, accNo);

        if (account != null) {
            System.out.println("Account Holder: " + account.getName());
            System.out.println("Available Balance: ₹" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
        session.close();
    }
}
