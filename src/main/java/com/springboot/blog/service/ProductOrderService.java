package com.springboot.blog.service;

import com.springboot.blog.entity.*;
import com.springboot.blog.repository.*;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderService  {
    private ProductOrderRepository productOrderRepository ;
 
    @Autowired
    public ProductOrderService(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    public Set<?> listAllProduct_Orders() {
        return (Set<?>) productOrderRepository.findAll();
    }


    public Product_Order getProduct_OrderById(int id) {
        return productOrderRepository.findById(id).orElse(null);
    }


    public Product_Order saveProduct_Order(@Valid Product_Order product_Order) {
        return productOrderRepository.save(product_Order);
    }


    public Product_Order updateProduct_Order(@Valid Product_Order product_Order, int id) {
        Product_Order find = (Product_Order) getProduct_OrderById(id);
        if(find != null ){
            saveProduct_Order(find);
        }
        return find;  
      }


    public void delete(int id) {
        Product_Order find = (Product_Order) getProduct_OrderById(id);
        if(find != null ){
            delete(id);
        }
    }
}
