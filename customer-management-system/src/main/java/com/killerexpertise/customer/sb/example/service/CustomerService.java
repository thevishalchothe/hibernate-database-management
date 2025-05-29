package com.killerexpertise.customer.sb.example.service;

import com.killerexpertise.customer.sb.example.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomer(Long id);

    void deleteCustomer(Long id);
}
