package com.springboot.blog.repository;


import org.springframework.data.repository.CrudRepository;

import com.springboot.blog.entity.Product_Order;

public interface ProductOrderRepository extends CrudRepository<Product_Order, Integer>{
}
