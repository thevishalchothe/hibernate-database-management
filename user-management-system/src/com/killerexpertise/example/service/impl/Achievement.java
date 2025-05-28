package com.killerexpertise.example.service.impl;

import java.util.List;
import java.util.Scanner;

import com.killerexpertise.example.config.HibernateUtil;
import com.killerexpertise.example.model.User;
import com.killerexpertise.example.service.Requirement;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Achievement implements Requirement {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void insertData() {
        Session session = null;
        Transaction tx = null;
        try {
            User u = new User();
            
            System.out.println("Enter User Id:");
            u.setId(sc.nextInt());
            System.out.println("Enter Name:");
            u.setName(sc.next());
            System.out.println("Enter Gender:");
            u.setGender(sc.next());
            System.out.println("Enter Address:");
            u.setAddress(sc.next());
            System.out.println("Enter MobNo:");
            u.setMobno(sc.nextLong());
            System.out.println("Enter Weight:");
            u.setWeight(sc.nextFloat());
            System.out.println("Enter Height:");
            u.setHeight(sc.nextDouble());
            System.out.println("Enter MailID:");
            u.setMailId(sc.next());

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(u);
            tx.commit();
            System.out.println("Data inserted successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void viewSingleData() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Enter User ID:");
            int id = sc.nextInt();
            
            User u = session.get(User.class, id);
            if (u != null) {
                System.out.println("User Details: " + u);
            } else {
                System.out.println("User not found with ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void viewAllData() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<User> users = session.createQuery("from User", User.class).getResultList();
            
            if (users.isEmpty()) {
                System.out.println("No users found in database.");
            } else {
                users.forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void updateData() {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Enter ID:");
            int id = sc.nextInt();
            User u = session.get(User.class, id);
            
            if (u == null) {
                System.out.println("User not found with ID: " + id);
                return;
            }

            System.out.println("Current User Details: " + u);
            System.out.println("Enter Choice: \n1.Name 2.Gender 3.Address 4.MobNo 5.Weight 6.Height 7.MailID");
            int choice = sc.nextInt();
            
            tx = session.beginTransaction();
            switch(choice) {
                case 1:
                    System.out.println("Enter New Name: ");
                    u.setName(sc.next());
                    break;
                case 2:
                    System.out.println("Enter New Gender: ");
                    u.setGender(sc.next());
                    break;
                case 3:
                    System.out.println("Enter New Address: ");
                    u.setAddress(sc.next());
                    break;
                case 4:
                    System.out.println("Enter New MobNo: ");
                    u.setMobno(sc.nextLong());
                    break;
                case 5:
                    System.out.println("Enter New Weight: ");
                    u.setWeight(sc.nextFloat());
                    break;
                case 6:
                    System.out.println("Enter New Height: ");
                    u.setHeight(sc.nextDouble());
                    break;
                case 7:
                    System.out.println("Enter New MailID: ");
                    u.setMailId(sc.next());
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    return;
            }
            session.update(u);
            tx.commit();
            System.out.println("Data updated successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void deleteSingleData() {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Enter ID: ");
            int id = sc.nextInt();
            
            User u = session.get(User.class, id);
            if (u == null) {
                System.out.println("User not found with ID: " + id);
                return;
            }

            tx = session.beginTransaction();
            session.delete(u);
            tx.commit();
            System.out.println("User deleted successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void deleteAllData() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<User> users = session.createQuery("from User", User.class).getResultList();
            
            if (users.isEmpty()) {
                System.out.println("No users found to delete.");
                return;
            }

            Transaction tx = session.beginTransaction();
            for (User u : users) {
                session.delete(u);
            }
            tx.commit();
            System.out.println("All users deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }
}