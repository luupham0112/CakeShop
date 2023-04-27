package com.phanmem.cakeshop.repository;

import com.phanmem.cakeshop.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    Admin findAdminByUserName(String username);

}
