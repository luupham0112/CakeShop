package com.phanmem.cakeshop.service;

import com.phanmem.cakeshop.entity.Customer;

public interface CustomerService {
    Customer save(Customer entity);

    Customer getByUsername(String username);

    Customer login(String userName, String passWord);
}
