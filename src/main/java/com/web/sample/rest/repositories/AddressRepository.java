package com.web.sample.rest.repositories;

import com.web.sample.rest.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
