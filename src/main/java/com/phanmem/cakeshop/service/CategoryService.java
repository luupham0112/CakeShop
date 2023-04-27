package com.phanmem.cakeshop.service;

import com.phanmem.cakeshop.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    Category save(Category entity);

    void deleteById(Long aLong);

    Optional<Category> findById(Long aLong);
}
