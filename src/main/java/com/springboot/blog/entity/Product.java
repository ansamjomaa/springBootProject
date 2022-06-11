package com.springboot.blog.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table
@Data
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_order_id")
    private Product_Order productOrders;

    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String reference;
    @Column(nullable = false)
    private boolean stockable;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double vat;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public boolean isStockable() {
        return stockable;
    }
    public void setStockable(boolean stockable) {
        this.stockable = stockable;
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
