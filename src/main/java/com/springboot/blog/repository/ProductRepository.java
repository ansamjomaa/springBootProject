package com.springboot.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.blog.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
