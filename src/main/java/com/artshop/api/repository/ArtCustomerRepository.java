package com.artshop.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.artshop.api.model.ArtCustomer;


@Repository
public interface ArtCustomerRepository extends CrudRepository<ArtCustomer, Integer>{
    public Iterable<ArtCustomer> findByEmail(String email);
}
