package com.web.sample.rest.repositories;

import com.web.sample.rest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value="select cust.*,addr.country from Customer cust join Address addr on cust.id = addr.customer_id " +
            "and addr.country = :country ")
    public List<Customer> findCustomersByCountry(String country);
}
