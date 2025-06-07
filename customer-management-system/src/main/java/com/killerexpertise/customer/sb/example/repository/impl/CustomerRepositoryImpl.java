package com.killerexpertise.customer.sb.example.repository.impl;

import com.killerexpertise.customer.sb.example.model.Customer;
import com.killerexpertise.customer.sb.example.repository.CustomerRepository;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Setter
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Customer save(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public Customer findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Customer.class, id);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Customer", Customer.class).list();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                session.remove(customer);
            }
            session.getTransaction().commit();
        }
    }

        @Override
        public List<Customer> findAll(int page, int size) {
            try (Session session = sessionFactory.openSession()) {
                return session.createQuery("from Customer", Customer.class)
                        .setFirstResult(page * size)
                        .setMaxResults(size)
                        .list();
            }
        }
}
