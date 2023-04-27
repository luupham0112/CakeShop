package com.phanmem.cakeshop.repository;

import com.phanmem.cakeshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
