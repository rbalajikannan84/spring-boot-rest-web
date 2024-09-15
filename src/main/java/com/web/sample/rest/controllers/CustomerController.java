package com.web.sample.rest.controllers;

import com.web.sample.rest.entity.Customer;
import com.web.sample.rest.error.CustomerNotFoundException;
import com.web.sample.rest.repositories.CustomerRepository;
import com.web.sample.rest.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        return customerService.findById(id);
    }

    @GetMapping("/country/{country}")
    public List<Customer> findCustomersByCountry(@PathVariable String country) {
        return customerService.findCustomersByCountry(country);
    }

    @PostMapping("/save")
    public Customer save(@Valid @RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public Customer update(@PathVariable Long id, @RequestBody Customer customer) throws CustomerNotFoundException {
        return customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws CustomerNotFoundException {
        customerService.deleteCustomerById(id);
        return "Customer with id " + id + " deleted Successfully";
    }

    @GetMapping("/list/{offset}/{pageSize}")
    public List<Customer> listCustomersWithPage(@PathVariable int offset,@PathVariable int pageSize) {
        List<Customer> customers = customerService.listCustomersWithPage(offset, pageSize);
        return customers;
    }

}
