package com.artshop.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.artshop.api.model.Product;
import com.artshop.api.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public Iterable<Product> getAll() {
        return productService.getAll();
    }
    

    @PostMapping("/products/add")
    public String createOne(@RequestBody Product product) {
        return productService.createOne(product);
    }

}
