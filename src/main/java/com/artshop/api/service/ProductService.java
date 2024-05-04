package com.artshop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artshop.api.model.Product;
import com.artshop.api.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAll(){
        return productRepository.findAll();
    }

    public String createOne(Product newProduct){
        productRepository.save(newProduct);
        return "product created";
    }


}
