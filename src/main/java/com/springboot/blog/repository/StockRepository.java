package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Stock;



public interface StockRepository extends JpaRepository<Stock, Integer>{
}
