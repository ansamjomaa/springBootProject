package com.springboot.blog.entity;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;
    
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
   @Column(nullable = false)
    private Date bornAt;

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBornAt() {
        return bornAt;
    }
    public void setBornAt(Date bornAt) {
        this.bornAt = bornAt;
    }

}
