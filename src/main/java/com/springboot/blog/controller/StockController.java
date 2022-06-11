package com.springboot.blog.controller;

import com.springboot.blog.entity.*;

import com.springboot.blog.service.StockService;
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

@Api(value = "CRUD Rest APIs for Stock resources")
@RestController
@RequestMapping()
public class StockController {

    private StockService service;

    public StockController(StockService service) {
        this.service = service;
    }

    @ApiOperation(value = "Create Stock REST API")
    @PreAuthorize("hasRole('ADMIN')")
    /*To support multiple roles with or condition*/
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    /*To support multiple roles with and condition*/
//    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")

    // create blog Stock rest api
    @PostMapping("/api/v1/Stocks")
    public ResponseEntity<Stock> createStock(@Valid @RequestBody Stock Stock) {
        return new ResponseEntity<>(service.saveStock(Stock), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Stocks REST API")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error!"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful retrieval",
                    response = Stock.class, responseContainer = "Response") })
    // get all Stock rest api
    @GetMapping("/api/v1/Stocks")
    public Set<?> getAllStocks() {
        return (Set<?>) service.listAllStocks();
    }

    @ApiOperation(value = "Get Stock By Id REST API")
    // get Stock by id
    @GetMapping(value = "/api/v1/Stock/{id}")
    public ResponseEntity<Stock> getStockByIdV1(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(service.getStockById(id));
    }

    @ApiOperation(value = "Update Stock By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // update Stock by id rest api
    @PutMapping("/api/v1/Stock/{id}")
    public ResponseEntity<Stock> updateStock(@Valid @RequestBody Stock Stock, @PathVariable(name = "id") int id) {

        Stock StockRes = service.updateStock(Stock, id);

        return new ResponseEntity<>(StockRes, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Stock By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // delete Stock rest api
    @DeleteMapping("/api/v1/Stock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable(name = "id") int id) {

        service.delete(id);

        return new ResponseEntity<>("Stockentity deleted successfully.", HttpStatus.OK);
    }
}
