package com.phanmem.cakeshop.repository;

import com.phanmem.cakeshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByProductId(Long id);

    @Query(value = "select p from Product p inner join Category c on c.categoryId = p.category.categoryId where c.categoryId = ?1 ")
    List<Product> findProductsByCategoryId(Long categoryId);

    @Query("select p from Product p where  p.name like %?1%")
    List<Product> searchProductsList(String keyword);


    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByPriceDesc();

}
