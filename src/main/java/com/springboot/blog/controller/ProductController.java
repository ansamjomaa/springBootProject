package com.springboot.blog.controller;

import com.springboot.blog.entity.*;

import com.springboot.blog.service.ProductService;
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

@Api(value = "CRUD Rest APIs for Product resources")
@RestController
@RequestMapping()
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @ApiOperation(value = "Create Product REST API")
    @PreAuthorize("hasRole('ADMIN')")
    /*To support multiple roles with or condition*/
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    /*To support multiple roles with and condition*/
//    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")

    // create blog Product rest api
    @PostMapping("/api/v1/Products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product Product) {
        return new ResponseEntity<>(service.saveProduct(Product), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Products REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Product.class, responseContainer = "Response") })
    // get all Product rest api
    @GetMapping("/api/v1/Products")
    public Set<?> getAllProducts() {
        return (Set<?>) service.listAllProducts();
    }

    @ApiOperation(value = "Get Product By Id REST API")
    // get Product by id
    @GetMapping(value = "/api/v1/Product/{id}")
    public ResponseEntity<Product> getProductByIdV1(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @ApiOperation(value = "Update Product By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // update Product by id rest api
    @PutMapping("/api/v1/Product/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product Product, @PathVariable(name = "id") int id) {

        Product ProductRes = service.updateProduct(Product, id);

        return new ResponseEntity<>(ProductRes, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Product By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // delete Product rest api
    @DeleteMapping("/api/v1/Product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {

        service.delete(id);

        return new ResponseEntity<>("Productentity deleted successfully.", HttpStatus.OK);
    }
}
