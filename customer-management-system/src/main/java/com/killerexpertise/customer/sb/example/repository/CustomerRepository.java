package com.killerexpertise.customer.sb.example.repository;


import com.killerexpertise.customer.sb.example.model.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);

    Customer findById(Long id);

    List<Customer> findAll();

    void deleteById(Long id);

    List<Customer> findAll(int page, int size); // method for pagination
}
