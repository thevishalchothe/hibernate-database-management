# User Management System Example ⚙️📂

This project example is a console-based Java application for managing user records using:
  -  Hibernate ORM (Object-Relational Mapping), MySQL database, and  Manual JAR dependency management (without Maven).

---


## Manual JAR Setup (Without Maven) 🛠️ 

1. Create a `lib` folder in your project root
2. Download and add these JAR files:

### Required JAR Files:

- **Hibernate Core**:
  
  - `hibernate-core-5.6.14.Final.jar`
  - `hibernate-commons-annotations-5.1.2.Final.jar`
  
- **MySQL Connector**:

  - `mysql-connector-java-8.0.30.jar`

- **Additional Dependencies**:

  - `javax.persistence-api-2.2.jar`
  - `jboss-logging-3.4.3.Final.jar`
  - `javassist-3.29.2-GA.jar`
  - `antlr-2.7.7.jar`

## How to Run 🚀 

1. **Set up MySQL Database**:

    - Ensure MySQL server is running
   - Create database `user_db` or let Hibernate create it automatically

3. **Configure Database**:

   - Edit `HibernateUtil.java` with your MySQL credentials:

   ```
   map.put(Environment.URL, "jdbc:mysql://localhost:3306/user_db");
   map.put(Environment.USER, "_username");
   map.put(Environment.PASS, "_password");
   ```

3. **Run the Application**:

    -  Execute Report.java as Java Application
    -  Follow the console menu to perform CRUD operations

---

## Technical Details ⚙️ 

  -  ORM Framework: Hibernate 5.6
  -  Database: MySQL 8.0
  -  Dialect: MySQL55Dialect (compatible with most MySQL versions)
  -  Schema Management: Automatic table creation/updates (hbm2ddl.auto=update)
