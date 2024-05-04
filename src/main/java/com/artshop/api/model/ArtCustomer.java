package com.artshop.api.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ArtCustomer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate created_at; 
    private LocalDate updated_at;

    public ArtCustomer(String email, String password, String firstname, String lastname){
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.created_at = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        this.updated_at = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    }
 
}


