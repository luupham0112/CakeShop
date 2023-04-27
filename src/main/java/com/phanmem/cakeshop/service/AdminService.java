package com.phanmem.cakeshop.service;

import com.phanmem.cakeshop.entity.Admin;

public interface AdminService {

    Admin save(Admin entity);

    Admin getAdminByUsername(String username);

    Admin login(String userName, String passWord);
}
