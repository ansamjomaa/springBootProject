package com.springboot.blog.service;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.*;
import com.springboot.blog.repository.*;


@Service
public class CustomerService  {
    private CustomerRepository customerRepository ;
 
    @Autowired
    public  CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Iterable<Customer> listAllCustomers() {
        return customerRepository.findAll();
    }

   
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }


    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public Customer updateCustomer(@Valid Customer customer, int id) {
        Customer find = getCustomerById(id);
        if(find != null ){
            saveCustomer(find);
        }
        return find;
    }


    public void delete(int id) {
        Customer find = getCustomerById(id);
        if(find != null ){
            delete(id);
        }
    }
}
