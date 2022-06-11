package com.springboot.blog.service;

import com.springboot.blog.entity.*;
import com.springboot.blog.repository.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService   {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(@Valid Product product, int id) {
        Product find =  getProductById(id);
        if(find != null ){
            saveProduct(find);
        }
        return find; 
    }

    public void delete(int id) {
        Product find =  getProductById(id);
        if(find != null ){
            delete(id);
        }
    }
}
