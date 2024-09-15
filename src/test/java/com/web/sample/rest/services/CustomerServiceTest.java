package com.web.sample.rest.services;

import com.web.sample.rest.entity.Address;
import com.web.sample.rest.entity.Customer;
import com.web.sample.rest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {
    private List<Address> addressList = new ArrayList<Address>();
    private List<Customer> customerList = new ArrayList<>();

    private Customer customer = new Customer();

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        Address address = Address.builder()
                                .street("Telok blangah")
                                .city("Singapore")
                                .build();

        addressList.add(address);

        customer = Customer.builder()
                            .id(1L)
                            .email("test@test.com")
                            .gender("Male")
                            .address(addressList)
                            .build();

        customerList.add(customer);
    }

    @Test
    void findById() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(customerList.stream().findFirst());
        Customer found = customerRepository.findById(1L).get();
        assertEquals(customer, found);
    }

    @Test
    void whenValidCountry_theReturnCustomersByCountry() {
        Mockito.when(customerRepository.findCustomersByCountry("Singapore")).thenReturn(customerList);
        String country = "Singapore";
        List<Customer> customers = customerRepository.findCustomersByCountry(country);
        assertEquals(customers, customerList);
    }
}