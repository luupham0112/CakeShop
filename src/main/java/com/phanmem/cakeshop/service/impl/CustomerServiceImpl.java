package com.phanmem.cakeshop.service.impl;

import com.phanmem.cakeshop.entity.Customer;
import com.phanmem.cakeshop.repository.CustomerRepository;
import com.phanmem.cakeshop.repository.RoleRepository;
import com.phanmem.cakeshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Customer save(Customer entity) {
        entity.setRoles(Arrays.asList(roleRepository.findByName("CUSTOMER")));
        return customerRepository.save(entity);
    }

    @Override
    public Customer getByUsername(String username){
        return  customerRepository.findByUsername(username);
    }


    @Override
    public Customer login(String userName, String passWord) {
        Customer optExist = customerRepository.findByUsername(userName);
        if(optExist!=null && bCryptPasswordEncoder.matches(passWord, optExist.getPassword())) {
            optExist.setPassword("");
            return optExist;
        }
        return null;
    }
}
