package com.springboot.blog.controller;

import com.springboot.blog.entity.*;

import com.springboot.blog.service.ProductOrderService;
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

@Api(value = "CRUD Rest APIs for Product_Order resources")
@RestController
@RequestMapping()
public class ProductOrderController {

    private ProductOrderService service;

    public ProductOrderController(ProductOrderService service) {
        this.service = service;
    }

    @ApiOperation(value = "Create Product_Order REST API")
    @PreAuthorize("hasRole('ADMIN')")
    /*To support multiple roles with or condition*/
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    /*To support multiple roles with and condition*/
//    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")

    // create blog Product_Order rest api
    @PostMapping("/api/v1/Product_Orders")
    public ResponseEntity<Product_Order> createProduct_Order(@Valid @RequestBody Product_Order Product_Order) {
        return new ResponseEntity<>(service.saveProduct_Order(Product_Order), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Product_Orders REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Product_Order.class, responseContainer = "Response") })
    // get all Product_Order rest api
    @GetMapping("/api/v1/Product_Orders")
    public Set<?> getAllProduct_Orders() {
        return (Set<?>) service.listAllProduct_Orders();
    }

    @ApiOperation(value = "Get Product_Order By Id REST API")
    // get Product_Order by id
    @GetMapping(value = "/api/v1/Product_Order/{id}")
    public ResponseEntity<Product_Order> getProduct_OrderByIdV1(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(service.getProduct_OrderById(id));
    }

    @ApiOperation(value = "Update Product_Order By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // update Product_Order by id rest api
    @PutMapping("/api/v1/Product_Order/{id}")
    public ResponseEntity<Product_Order> updateProduct_Order(@Valid @RequestBody Product_Order Product_Order, @PathVariable(name = "id") int id) {

        Product_Order Product_OrderRes = service.updateProduct_Order(Product_Order, id);

        return new ResponseEntity<>(Product_OrderRes, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Product_Order By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // delete Product_Order rest api
    @DeleteMapping("/api/v1/Product_Order/{id}")
    public ResponseEntity<String> deleteProduct_Order(@PathVariable(name = "id") int id) {

        service.delete(id);

        return new ResponseEntity<>("Product_Orderentity deleted successfully.", HttpStatus.OK);
    }
}
