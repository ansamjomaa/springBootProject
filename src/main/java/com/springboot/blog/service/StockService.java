package com.springboot.blog.service;


import com.springboot.blog.entity.*;
import com.springboot.blog.repository.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService  {
    private StockRepository stockRepository ;
 
    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


    public Iterable<Stock> listAllStocks() {
        return stockRepository.findAll();
    }

   
    public Stock getStockById(Integer id) {
        return stockRepository.findById(id).orElse(null);
    }


    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(@Valid Stock stock, int id) {
        Stock find =  getStockById(id);
        if(find != null ){
            saveStock(find);
        }
        return find;     }


    public void delete(int id) {
        Stock find =  getStockById(id);
        if(find != null ){
            delete(id);
        }
    }
}
