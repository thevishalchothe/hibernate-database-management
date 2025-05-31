# Student Hibernate Management System ⚙️🔧

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

### ➡️ Quick Steps to Use Hibernate with c3p0 and XML Mapping 

  1.  Add dependencies (Hibernate, MySQL connector, c3p0) in pom.xml.
  2.  Create hibernate.cfg.xml (in src/main/resources):
  3.  Configure DB connection.
  4.  Enable c3p0 pooling with <property name="hibernate.c3p0.*">.
  5.  Add `<mapping resource="student.hbm.xml"/>`.
  6.  Create c3p0-config.xml (in src/main/resources) with connection pool settings.
  7.  Create Hibernate mapping student.hbm.xml for Student entity (in src/main/resources).
  8.  Create Student Java class with fields and getters/setters.
  9.  Create HibernateConfig class to build and provide Hibernate SessionFactory.
  10.  Write DAO methods using Hibernate sessions for CRUD (e.g., save, update).
  11.  Run the application, Hibernate uses c3p0 pool for DB connections automatically.

---
 
## Setup Hibernate with c3p0 and XML Mapping and Sample Code 💡🛠️

###  Step 1: HibernateConfig.java 

HibernateConfig class to build and provide Hibernate SessionFactory.

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
Builds the `SessionFactory` from `hibernate.cfg.xml`.

---

### Step 2: hibernate.cfg.xml ⚙️

This file configures Hibernate and sets up database connection, dialect.

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

        <!-- Mapping file -->
        <mapping resource="student.hbm.xml"/>

    </session-factory>
</hibernate-configuration>
```
---

### Step 3: Add c3p0-config.xml

Create c3p0-config.xml in src/main/resources/ with:

```
<c3p0-config>
    <default-config>
        <property name="initialPoolSize">5</property>
        <property name="minPoolSize">5</property>
        <property name="maxPoolSize">10</property>
        <property name="checkoutTimeout">3000</property>
        <property name="maxStatements">200</property>
        <property name="idleConnectionTestPeriod">300</property>
        <property name="maxIdleTime">30</property>
        <property name="preferredTestQuery">SELECT 1 FROM DUAL</property>
        <property name="testConnectionOnCheckin">true</property>
        <property name="testConnectionOnCheckout">false</property>
    </default-config>
</c3p0-config>
```
This file configures advanced c3p0 pool parameters.

---

### Step 4: Create Hibernate Mapping XML - student.hbm.xml

Place this file in src/main/resources/:

```
<?xml version = "1.0" encoding = "utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

<hibernate-mapping>
    <class name="com.killerexpertise.example.model.Student" table="STUDENT">
        <id name="id" column="ID" type="int">
            <generator class="native"/>
        </id>
        <property name="name" column="NAME" type="string"/>
        <property name="percentage" column="PERCENTAGE" type="double"/>
    </class>
</hibernate-mapping>
```

