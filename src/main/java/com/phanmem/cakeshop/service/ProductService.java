package com.phanmem.cakeshop.service;

import com.phanmem.cakeshop.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product entity);

    List<Product> findAll();

    Optional<Product> findById(Long aLong);

    Product getById(Long id);

    void deleteById(Long aLong);

    List<Product> findProductByCategoryId(Long id);

    Page<Product> pageProducts(int pageNo);

    Page<Product> searchProducts(int pageNo, String keyword);

    List<Product> sortByHighPrice();

    List<Product> sortByLowPrice();
}
