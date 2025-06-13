package com.expertise.caching.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HibernateEhcacheIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateEhcacheIntegrationApplication.class, args);
	}

}
