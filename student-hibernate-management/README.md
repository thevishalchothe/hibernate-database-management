# Student Hibernate Management System ➡️⚙️

This project demonstrates a simple `student-hibernate-management` using **Hibernate ORM** with **XML-based configuration** (no annotations), integrated with **c3p0 connection pooling** for efficient database connection management.

---

## What is it?

A simple CRUD-based Java application that:
- Uses **Hibernate** for ORM (Object Relational Mapping).
- Stores and retrieves student data from a **MySQL** database.
- Is fully configured using **XML files**, making it annotation-free.
- Utilizes **c3p0 connection pooling** to improve performance and manage database resources effectively.

---

## How It Works

  -  HibernateConfig.java builds the SessionFactory from hibernate.cfg.xml.
  -  StudentDao.java performs basic CRUD operations using Hibernate sessions.
  -  MainApp.java is the entry point that calls DAO methods for adding or retrieving student data.
  -   Hibernate uses the student.hbm.xml file to map Java class fields to database table columns.
  -   c3p0 provides connection pooling to improve DB access efficiency.

 ---
##  Sample Code 💡

###  HibernateConfig.java 🛠️

```
package com.killerexpertise.example.config;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class HibernateConfig {

    public static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    @Bean
    private static SessionFactory buildSessionFactory() {
        try {
            // Automatically loads hibernate.cfg.xml from resources
            return new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            System.err.println("Session factory creation failed: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void shutdown() {
        SESSION_FACTORY.close();
    }
}

```
➡️ Builds the `SessionFactory` from `hibernate.cfg.xml`.

###  hibernate.cfg.xml ⚙️
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>

        <!-- Database connection settings -->
        <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/student_db</property>
        <property name="connection.username">root</property>
        <property name="connection.password">root</property>

        <!-- Hibernate properties -->
        <property name="dialect">org.hibernate.dialect.MySQL8Dialect</property>
        <property name="show_sql">true</property>
        <property name="format_sql">true</property>
        <property name="hbm2ddl.auto">update</property>
        <property name="current_session_context_class">thread</property>

        <!-- C3P0 settings -->
        <property name="hibernate.c3p0.min_size">5</property>
        <property name="hibernate.c3p0.max_size">10</property>
        <property name="hibernate.c3p0.timeout">300</property>
        <property name="hibernate.c3p0.max_statements">200</property>
        <property name="hibernate.c3p0.idle_test_period">300</property>
        <property name="hibernate.c3p0.acquire_increment">2</property>
        <property name="hibernate.c3p0.testConnectionOnCheckin">true</property>

        <!-- Mapping file -->
        <mapping resource="student.hbm.xml"/>

    </session-factory>
</hibernate-configuration>
```

🗂️ `student.hbm.xml`  
➡️ Maps Java class fields to database table columns.

🌐 `c3p0`  
➡️ Provides connection pooling for efficient DB access.
