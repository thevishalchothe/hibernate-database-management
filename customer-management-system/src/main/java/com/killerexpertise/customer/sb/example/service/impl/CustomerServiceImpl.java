package com.killerexpertise.customer.sb.example.service.impl;

import com.killerexpertise.customer.sb.example.model.Customer;
import com.killerexpertise.customer.sb.example.repository.CustomerRepository;
import com.killerexpertise.customer.sb.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers(int page, int size) {
        return customerRepository.findAll(page, size);
    }
}
