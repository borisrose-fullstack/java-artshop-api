package com.artshop.api.security;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {

	@Value("${jwt.secretKey}")
	private String secretKey;
	// @Bean
	// public FilterRegistrationBean<MyCustomFilter>
	// myCustomFilterRegistrationBean(MyCustomFilter filter) {
	// FilterRegistrationBean<MyCustomFilter> registration = new
	// FilterRegistrationBean<>(filter);
	// registration.setEnabled(false);
	// return registration;
	// }

	// @Autowired
	// private MyCustomUserDetailsService myCustomUserDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.addFilterBefore(new MyCustomFilter(), AuthorizationFilter.class)
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/products", "/login", "/register").permitAll()
						.anyRequest().authenticated())
				.oauth2ResourceServer((oauth2) -> oauth2
						.jwt(org.springframework.security.config.Customizer.withDefaults()));

		return http.build();
	}

	// @Bean
	// public UserDetailsService userDetailsService() {
	// UserDetails userDetails = User.withDefaultPasswordEncoder()
	// .username("user")
	// .password("password")
	// .roles("USER")
	// .build();

	// return new InMemoryUserDetailsManager(userDetails);
	// }

	@Bean
	public AuthenticationManager authenticationManager(
			MyCustomUserDetailsService myCustomUserDetailsService,
			PasswordEncoder passwordEncoder) {

		System.out.print("into AM");

		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(myCustomUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		SecretKey key = new SecretKeySpec(secretKey.getBytes(), "HMACSHA256");
		return NimbusJwtDecoder.withSecretKey(key).build();
	}

	@Bean
	public JwtEncoder jwtEncoder() {
		return new NimbusJwtEncoder(new ImmutableSecret<>(this.secretKey.getBytes()));
	}

}