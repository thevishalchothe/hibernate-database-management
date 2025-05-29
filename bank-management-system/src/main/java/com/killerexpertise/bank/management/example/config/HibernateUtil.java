package com.config;

import com.killerexpertise.bank.management.example.model.Account;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;


public class HibernateUtil {
    private static SessionFactory sf;
    private static StandardServiceRegistry registry;

    public static SessionFactory getSessionFactory() {
        try {
            if (sf == null) {
                // connection properties
                Map<String, String> map = new HashMap<>();
                map.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                map.put(Environment.URL, "jdbc:mysql://localhost:3306/Bank_Project?createDatabaseIfNotExist=true");
                map.put(Environment.USER, "root");
                map.put(Environment.PASS, "root");
                //hibernate properties
                map.put(Environment.DIALECT, "org.hibernate.dialect.MySQL55Dialect");
                map.put(Environment.HBM2DDL_AUTO, "update");
                map.put(Environment.SHOW_SQL, "true");


                // create object of StandardServiceRegistryBuilder
                registry = new StandardServiceRegistryBuilder().applySettings(map).build();
                // create object of MetaDataSources
                MetadataSources mds = new MetadataSources(registry);
                mds.addAnnotatedClass(Account.class);
                // create object of MetaData
                Metadata md = mds.getMetadataBuilder().build();
                // create object of SessionFactory
                sf = md.getSessionFactoryBuilder().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sf;
    }

}
