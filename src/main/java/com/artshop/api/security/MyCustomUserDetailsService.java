package com.artshop.api.security;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.artshop.api.model.ArtCustomer;
import com.artshop.api.repository.ArtCustomerRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ArtCustomerRepository artCustomerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        System.out.println("username" + username);

        System.out.println("---------------");
        Iterable<ArtCustomer> result = artCustomerRepository.findByEmail(username);
        System.out.println("---------------");
        if (result == null ) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        System.out.print("result" + result);

        ArtCustomer artCustomer = result.iterator().next();

        if (artCustomer.equals(null)) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        System.out.print(artCustomer);
        

        return User.withUsername(artCustomer.getEmail())
                .password(artCustomer.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
