# Customer Management System Example ⚙️✔️

This is a Spring Boot application for managing customers using **manual Hibernate integration** (without Spring Data JPA). 
  -  This application demonstrates how to configure Hibernate manually, manage the database via SessionFactory, and build a fully functional RESTful API with CRUD operations.

---


## Project Features 📌 

- Add, view, update, and delete customers
- Manual Hibernate configuration (no Spring Data JPA)
- RESTful APIs using Spring MVC
- MySQL database integration
- Lombok for cleaner models
- Structured into layers: Controller, Service, Repository

---

##  Manual Hibernate Setup ⚙️

Instead of using Spring Data JPA, the project manually configures Hibernate via:

  ```java
  @Configuration
  public class HibernateConfig {
  
      @Bean
      public SessionFactory sessionFactory() {
          // Create registry, metadata, and session factory manually
      }
  }
  ```
This SessionFactory is injected in the Repository layer to perform CRUD operations using Hibernate sessions.

---

## Hibernate Configuration Explained 🛠️ 

Instead of Spring Boot auto-configuring JPA and `EntityManager`, we configure Hibernate manually using the `SessionFactory`. This gives you fine-grained control over how Hibernate interacts with your database.

###  Configuration File: `HibernateConfig.java` 📝

The `HibernateConfig` class is responsible for manually setting up Hibernate and creating a singleton `SessionFactory`. Here's what it does:

1. Database Connection Settings  
   These are configured directly in the code:
   ```java
   settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
   settings.put(Environment.URL, "jdbc:mysql://localhost:3306/customer_crud_db");
   settings.put(Environment.USER, "root");
   settings.put(Environment.PASS, "root");
    ```
   
Alternatively, you can fetch these from `application.properties` using `@Value` or `Environment`, but here we keep it hardcoded for simplicity.

2. Hibernate Settings 

  ```java
  settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
  settings.put(Environment.HBM2DDL_AUTO, "update");
  settings.put(Environment.SHOW_SQL, "true");
  ```

  -  HBM2DDL_AUTO=update: Automatically updates the schema based on entity classes.
  -  SHOW_SQL=true: Enables printing of SQL queries in the console.

3. Service Registry and Metadata
 
  ```
  StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
      .applySettings(settings)
      .build();
  ```
  -  This builds a StandardServiceRegistry with all the above settings.

4.  Registering Annotated Entities
 
  ```
  MetadataSources sources = new MetadataSources(registry)
      .addAnnotatedClass(Customer.class);
  ```

  -  This adds your entity classes (e.g., Customer) to the Hibernate configuration.

5.  Building SessionFactory 
  
  ```
  Metadata metadata = sources.getMetadataBuilder().build();
  sf = metadata.getSessionFactoryBuilder().build();
  ```

  -  This creates the SessionFactory, which can now be injected into your repository layer for direct database access using Hibernate.
