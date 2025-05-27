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
