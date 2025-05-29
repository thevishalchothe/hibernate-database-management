# Bank Management System Example ⚙️🏦

This project example is a console-based Bank Management System implemented in Java  for managing user records using: 
  -  Hibernate ORM (Object-Relational Mapping), MySQL as the backend database, and Maven for JAR dependency management.

---

## Project Description 📌 

This **Bank Management System** allows users to perform banking operations such as:

- Create a new bank account ✅ 
- Deposit money 💰 
- Withdraw money 💸
- Check balance 👁️‍🗨️
- View all account details 📋 

This project is a great example of how to use **Hibernate (ORM)** in a real-world use case with **Java 11**, **MySQL**, and **console-based UI (Scanner)**.

---

## Technologies Used 🧱 

| Technology | Description                             |
|------------|-----------------------------------------|
| Java 11    | Core programming language               |
| Hibernate  | ORM tool for database interaction       |
| MySQL      | Database for storing account info       |
| Maven      | Dependency management and build tool    |

---


---

## 🔑 Features

| Feature             | Description |
|---------------------|-------------|
| ➕ Create Account    | Validate input like account number, Aadhar, name, etc., then save |
| 💵 Deposit Money     | Add amount to the existing balance after validation |
| 💳 Withdraw Money    | Subtract amount, keeping minimum balance requirement |
| 🔎 Check Balance     | Shows the current balance |
| 📑 Display Details   | List all account details in a readable format |

---

## 🔧 How to Run the Project

1. **Clone the repository**
  
  ```
   git clone https://github.com/thevishalchothe/hibernate-database-management.git
   cd bank-management-system
  ```

2.  Configure MySQL

  -  Create a database (e.g., bank_db)
  -  Update your DB username/password in hibernate.cfg.xml

3.  Build and run the project

  -  Use your IDE (like IntelliJ or Eclipse)
  -  Or run via terminal:
    
  ```  
  mvn clean install
  java -cp target/classes com.killerexpertise.bank.management.example.MainApp
  ```

---

## Validations & Business Rules

  -  Account number must be 8 digits ✔️ 
  -  Mobile number must be 10 digits 📱 
  -  Aadhar number must be 12 digits 🆔 
  -  Age must be ≥ 18 🎂 
  -  Initial balance must be ≥ ₹1000 💰 
  -  Name should not contain digits or symbols  👨‍🦰




