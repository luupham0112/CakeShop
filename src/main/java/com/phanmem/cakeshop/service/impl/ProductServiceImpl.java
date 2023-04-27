package com.phanmem.cakeshop.service.impl;

import com.phanmem.cakeshop.entity.Product;
import com.phanmem.cakeshop.repository.ProductRepository;
import com.phanmem.cakeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return productRepository.findById(aLong);
    }
    @Override
    public  Product getById(Long id){
        return  productRepository.findByProductId(id);
    }

    @Override
    public void deleteById(Long aLong) {
        productRepository.deleteById(aLong);
    }

    @Override
    public List<Product> findProductByCategoryId(Long id) {
        return productRepository.findProductsByCategoryId(id);
    }
    @Override
    public Page<Product> pageProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<Product> products = productRepository.findAll();
        Page<Product> productPages = toPage(products, pageable);
        return productPages;
    }
    private Page toPage(List<Product> list , Pageable pageable){
        if(pageable.getOffset() >= list.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }

    @Override
    public Page<Product> searchProducts(int pageNo, String keyword) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<Product> productDtoList = productRepository.searchProductsList(keyword);
        Page<Product> products = toPage(productDtoList, pageable);
        return products;
    }

    @Override
    public List<Product> sortByHighPrice(){
        return productRepository.findByOrderByPriceDesc();

    }
    @Override
    public List<Product> sortByLowPrice(){
        return productRepository.findByOrderByPriceAsc();
    }





}
