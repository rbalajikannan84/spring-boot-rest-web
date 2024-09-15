package com.web.sample.rest.services.impl;

import com.web.sample.rest.entity.Customer;
import com.web.sample.rest.error.CustomerNotFoundException;
import com.web.sample.rest.repositories.CustomerRepository;
import com.web.sample.rest.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Long id, Customer customer) throws CustomerNotFoundException {
        Optional<Customer> oCustomer = customerRepository.findById(id);
        if(oCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer with id "+id+" not available.");
        }
        Customer customerToUpdate = oCustomer.get();
        customer.setId(id);
        BeanUtils.copyProperties(customer, customerToUpdate);
        return customerRepository.save(customerToUpdate);

    }

    @Override
    public Customer findById(Long id) throws CustomerNotFoundException {
        Optional<Customer> oCustomer = customerRepository.findById(id);
        if(oCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer with id "+id+" not available.");
        }
        return oCustomer.orElse(null);
    }

    @Override
    public void deleteCustomerById(Long id) throws CustomerNotFoundException {
        Optional<Customer> oCustomer = customerRepository.findById(id);
        if(oCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer with id "+id+" not available.");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findCustomersByCountry(String country) {
        return customerRepository.findCustomersByCountry(country);
    }

    @Override
    public List<Customer> listCustomersWithPage(int offset,int pageSize){
        Pageable pageable = PageRequest.of(offset,pageSize,Sort.by("id").ascending());
        Page<Customer> pageList = customerRepository.findAll(pageable);
        return pageList.getContent();
    }
}
