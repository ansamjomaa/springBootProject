package com.springboot.blog.service;

import com.springboot.blog.entity.*;
import com.springboot.blog.repository.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService  {
    private OrderRepository orderRepository ;
 
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Iterable<Order> listAllOrders() {
        return orderRepository.findAll();
    }

   
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }


    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(@Valid Order order, int id) {
        Order find = getOrderById(id);
        if(find != null ){
            saveOrder(find);
        }
        return find;
    }

    public void delete(int id) {
        Order find = getOrderById(id);
        if(find != null ){
            delete(id);
        }
    }
    
}
