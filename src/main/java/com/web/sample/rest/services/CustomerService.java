package com.web.sample.rest.services;

import com.web.sample.rest.entity.Customer;
import com.web.sample.rest.error.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer save(Customer customer);

    public List<Customer> findAll();

    public Customer update(Long id, Customer customer) throws CustomerNotFoundException;

    public Customer findById(Long id) throws CustomerNotFoundException;

    public void deleteCustomerById(Long id) throws CustomerNotFoundException;

    public List<Customer> findCustomersByCountry(String country);

    public List<Customer> listCustomersWithPage(int page, int offset);
}
