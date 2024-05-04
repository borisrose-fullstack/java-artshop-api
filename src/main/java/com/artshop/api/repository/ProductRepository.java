package com.artshop.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.artshop.api.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    
}
    

