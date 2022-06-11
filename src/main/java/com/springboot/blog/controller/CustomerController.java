package com.springboot.blog.controller;

import com.springboot.blog.entity.Customer;

import com.springboot.blog.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.mapping.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import javax.validation.Valid;

@Api(value = "CRUD Rest APIs for Customer resources")
@RestController
@RequestMapping()
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    /*To support multiple roles with or condition*/
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    /*To support multiple roles with and condition*/
//    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")

    // create blog customer rest api
    @PostMapping("/api/v1/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(service.saveCustomer(customer), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Customers REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Customer.class, responseContainer = "Response") })
    // get all customer rest api
    @GetMapping("/api/v1/customers")
    public Set<?> getAllCustomers() {
        return (Set<?>) service.listAllCustomers();
    }

    @ApiOperation(value = "Get Customer By Id REST API")
    // get customer by id
    @GetMapping(value = "/api/v1/customer/{id}")
    public ResponseEntity<Customer> getCustomerByIdV1(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(service.getCustomerById(id));
    }

    @ApiOperation(value = "Update Customer By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // update customer by id rest api
    @PutMapping("/api/v1/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer, @PathVariable(name = "id") int id) {

        Customer customerRes = service.updateCustomer(customer, id);

        return new ResponseEntity<>(customerRes, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Customer By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // delete customer rest api
    @DeleteMapping("/api/v1/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") int id) {

        service.delete(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}
