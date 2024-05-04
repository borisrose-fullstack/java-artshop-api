package com.artshop.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artshop.api.model.ArtCustomer;
import com.artshop.api.model.RegisteringUser;
import com.artshop.api.repository.ArtCustomerRepository;


@Service
public class ArtCustomerService {
    

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ArtCustomerRepository artCustomerRepository;

    public Iterable<ArtCustomer> getAll(){
        return artCustomerRepository.findAll();
    }

    public ArtCustomer createOne(RegisteringUser user){

        ArtCustomer artCustomer = new ArtCustomer(user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getFirstname(), user.getLastname());
        return artCustomerRepository.save(artCustomer);
    }

    
   
}
