package com.web.sample.rest.repositories;

import com.web.sample.rest.entity.Address;
import com.web.sample.rest.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {
    private List<Address> addressList = new ArrayList<Address>();
    private List<Customer> customerList = new ArrayList<>();

    private Customer customer = new Customer();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Address address = Address.builder()
                .houseNumber("123")
                .street("Telok blangah")
                .city("Singapore")
                .build();

        addressList.add(address);

        customer = Customer.builder()
                .name("test")
                .email("test@test.com")
                .gender("Male")
                .address(addressList)
                .build();

        customerList.add(customer);
    }

    @Test
    void findById_thenReturnCustomer() {
        entityManager.persist(customer);
        Optional<Customer> queriedCustomer = customerRepository.findById(1L);
        assertEquals(customer, queriedCustomer.get());
    }

    @Test
    void saveCustomer_thenReturnCustomer() {
        customerRepository.save(customer);
        Optional<Customer> queriedCustomer = customerRepository.findById(customer.getId());
        assertEquals(customer, queriedCustomer.get());
    }

    @Test
    void deleteCustomer_thenReturnNoRecord() {
        customerRepository.save(customer);
        customerRepository.deleteById(customer.getId());
        assertEquals(0, customerRepository.findAll().size());
    }
}