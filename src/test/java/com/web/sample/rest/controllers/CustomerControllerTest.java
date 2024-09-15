package com.web.sample.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.sample.rest.entity.Customer;
import com.web.sample.rest.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void saveCustomerReturnsStatusOk() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");
        customer.setEmail("test@example.com");
        customer.setGender("Male");

        when(customerService.save(any())).thenReturn(customer);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/customer/save")
                 .contentType("application/json")
                 .content(objectMapper.writeValueAsString(customer)))
                 .andExpect(status().isOk());
    }

    @Test
    public void saveCustomerReturnsStatusBadRequestForInvalidEmail() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");
        customer.setEmail("invalid-email");
        customer.setGender("Male");

        when(customerService.save(any())).thenReturn(customer);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/customer/save")
                 .contentType("application/json")
                 .content(objectMapper.writeValueAsString(customer)))
                 .andExpect(status().isBadRequest());
    }

    @Test
    public void saveCustomerReturnsStatusBadRequestForInvalidGender() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");
        customer.setEmail("invalid-email");
        customer.setGender("X");

        when(customerService.save(any())).thenReturn(customer);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/customer/save")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateCustomerReturnsStatusOk() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");
        customer.setEmail("test@example.com");
        customer.setGender("M");

        when(customerService.update(1L, customer)).thenReturn(customer);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(put("/customer/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customer)))
                        .andExpect(status().isOk());
    }

    @Test
    public void deleteCustomerReturnsStatusOk() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");
        customer.setEmail("test@example.com");
        customer.setGender("M");

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(delete("/customer/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());
    }

    @Test
    public void getCustomerByIdReturnsStatusOk() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");
        customer.setEmail("test@example.com");
        customer.setGender("M");

        when(customerService.findById(1L)).thenReturn(customer);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(get("/customer/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());
    }
}