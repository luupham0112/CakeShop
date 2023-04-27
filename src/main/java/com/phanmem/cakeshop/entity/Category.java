package com.phanmem.cakeshop.entity;

import com.phanmem.cakeshop.dto.ProductDto;
import javax.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;
    private String name;
    @OneToMany(mappedBy="category", cascade = CascadeType.ALL)
    private Set<Product> products;
}
