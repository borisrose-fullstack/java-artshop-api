package com.artshop.api.controller;
import org.springframework.web.bind.annotation.RestController;
import com.artshop.api.model.ArtCustomer;
import com.artshop.api.model.RegisteringUser;
import com.artshop.api.service.ArtCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class ArtCustomerController {

    @Autowired
    private ArtCustomerService artCustomerService;
    
    @GetMapping("/artcustomers")
    public Iterable<ArtCustomer> getAll(@RequestParam String param) {
        return  artCustomerService.getAll();
    }
    
    @PostMapping("/register")
    public String createOne(@RequestBody SigningUpUser user) {

        RegisteringUser registeringUser = new RegisteringUser(user.email, user.password, user.firstname, user.lastname);
        artCustomerService.createOne(registeringUser); 
        return "user created";
    }

    public record SigningUpUser(String email, String password, String firstname, String lastname) {
    }
     
 
}
