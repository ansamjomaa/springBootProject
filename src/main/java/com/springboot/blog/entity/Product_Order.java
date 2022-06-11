package com.springboot.blog.entity;

import java.util.Collection;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table
@Data
public class Product_Order {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "product_order_id")
    // private int id;
    
    @Id
    @OneToMany(mappedBy = "productOrders")
    private Collection<Product> products;
    @Id
    @OneToMany(mappedBy = "productOrders")
    private Collection<Order> orders;
    
    @Column(nullable = false)
    private int productId;
    @Column(nullable = false)
    private int orderId;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double vat;

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getVat() {
        return vat;
    }
    public void setVat(double vat) {
        this.vat = vat;
    }


   
}
