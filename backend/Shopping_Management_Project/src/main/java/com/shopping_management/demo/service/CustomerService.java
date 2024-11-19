package com.shopping_management.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping_management.demo.model.Customer;
import com.shopping_management.demo.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void createCustomer(Customer customer) {
        repository.save(customer);
    }

    public void updateCustomer(Integer id, Customer customer) {
        customer.setCustomerId(id); 
        repository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        repository.deleteById(id);
    }
}
