package com.phanmem.cakeshop.service.impl;

import com.phanmem.cakeshop.entity.Admin;
import com.phanmem.cakeshop.repository.AdminRepository;
import com.phanmem.cakeshop.repository.RoleRepository;
import com.phanmem.cakeshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Admin save(Admin entity) {
        entity.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(entity);
    }
    @Override
    public Admin getAdminByUsername(String username){
        return  adminRepository.findAdminByUserName(username);
    }

    @Override
    public Admin login(String userName, String passWord) {
        Admin optExist = adminRepository.findAdminByUserName(userName);
        if(optExist!=null && bCryptPasswordEncoder.matches(passWord, optExist.getPassWord())) {
            optExist.setPassWord("");
            return optExist;
        }
        return null;
    }

}
