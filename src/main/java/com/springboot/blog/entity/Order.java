package com.springboot.blog.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
@Table
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @OneToMany(mappedBy = "order")
    private Set<Customer> customers;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_order_id")
    private Product_Order productOrders;

    @Column(nullable = false)
    private int customerId;
    @Column(nullable = false)
    private Date orderAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(Date orderAt) {
        this.orderAt = orderAt;
    }


   
}
