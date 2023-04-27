package com.phanmem.cakeshop.repository;

import com.phanmem.cakeshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUsername(String username);
}
