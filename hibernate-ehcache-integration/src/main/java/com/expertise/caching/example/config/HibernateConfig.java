package com.expertise.caching.example.config;

import com.expertise.caching.example.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() {
        Map<String, Object> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "org.h2.Driver");
        settings.put(Environment.URL, "jdbc:h2:mem:testdb");
        settings.put(Environment.USER, "sa");
        settings.put(Environment.PASS, "");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        settings.put(Environment.HBM2DDL_AUTO, "create-drop");
        settings.put(Environment.SHOW_SQL, "true");
        
        // Second-level cache configuration
        settings.put(Environment.USE_SECOND_LEVEL_CACHE, "true");
        settings.put(Environment.USE_QUERY_CACHE, "true");
        settings.put(Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        settings.put(Environment.CACHE_PROVIDER_CONFIG, "classpath:ehcache.xml");
        
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();

        return new MetadataSources(registry)
                .addAnnotatedClass(Product.class)
                .buildMetadata()
                .buildSessionFactory();
    }
}