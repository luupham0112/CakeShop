package com.phanmem.cakeshop.repository;


import com.phanmem.cakeshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
