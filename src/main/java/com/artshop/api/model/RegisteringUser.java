package com.artshop.api.model;

import lombok.Data;

@Data
public class RegisteringUser {
    
    private String email;
    private String password;
    private String firstname;
    private String lastname;

    public RegisteringUser(String email, String password, String firstname, String lastname){
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
