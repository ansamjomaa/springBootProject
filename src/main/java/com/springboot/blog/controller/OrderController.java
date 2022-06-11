package com.springboot.blog.controller;

import com.springboot.blog.entity.*;

import com.springboot.blog.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import javax.validation.Valid;

@Api(value = "CRUD Rest APIs for Order resources")
@RestController
@RequestMapping()
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @ApiOperation(value = "Create Order REST API")
    @PreAuthorize("hasRole('ADMIN')")
    /*To support multiple roles with or condition*/
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    /*To support multiple roles with and condition*/
//    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")

    // create blog Order rest api
    @PostMapping("/api/v1/Orders")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order Order) {
        return new ResponseEntity<>(service.saveOrder(Order), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Orders REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Order.class, responseContainer = "Response") })
    // get all Order rest api
    @GetMapping("/api/v1/Orders")
    public Set<?> getAllOrders() {
        return (Set<?>) service.listAllOrders();
    }

    @ApiOperation(value = "Get Order By Id REST API")
    // get Order by id
    @GetMapping(value = "/api/v1/Order/{id}")
    public ResponseEntity<Order> getOrderByIdV1(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(service.getOrderById(id));
    }

    @ApiOperation(value = "Update Order By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // update Order by id rest api
    @PutMapping("/api/v1/Order/{id}")
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order Order, @PathVariable(name = "id") int id) {

        Order OrderRes = service.updateOrder(Order, id);

        return new ResponseEntity<>(OrderRes, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Order By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // delete Order rest api
    @DeleteMapping("/api/v1/Order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {

        service.delete(id);

        return new ResponseEntity<>("Orderentity deleted successfully.", HttpStatus.OK);
    }
}
