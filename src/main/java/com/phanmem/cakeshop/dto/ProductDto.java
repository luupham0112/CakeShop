package com.phanmem.cakeshop.dto;

import javax.persistence.*;
import lombok.Data;

@Data
public class ProductDto {
    private Long productId;
    private String name;
    private int price;
    private String imageName;
    private Long categoryId;


}
