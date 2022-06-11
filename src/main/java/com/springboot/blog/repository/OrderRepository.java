package com.springboot.blog.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.blog.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
}
