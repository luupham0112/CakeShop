package com.phanmem.cakeshop.service.impl;

import com.phanmem.cakeshop.entity.Category;
import com.phanmem.cakeshop.repository.CategoryRepository;
import com.phanmem.cakeshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        categoryRepository.deleteById(aLong);
    }

    @Override
    public Optional<Category> findById(Long aLong) {
        return categoryRepository.findById(aLong);
    }
}
