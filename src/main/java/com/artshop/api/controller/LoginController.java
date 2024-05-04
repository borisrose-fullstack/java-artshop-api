package com.artshop.api.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.artshop.api.security.JWTService;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired JWTService jwtService;

	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) throws URISyntaxException {

		System.out.println("into Login" + loginRequest.email + loginRequest.password);

		Authentication authenticationRequest =
			UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email, loginRequest.password);

			System.out.print(authenticationRequest);


		Authentication authenticationResponse =
			this.authenticationManager.authenticate(authenticationRequest);
		
			String token = jwtService.generateToken(authenticationResponse);

			return token;
        
	}

	public record LoginRequest(String email, String password) {
	}

}