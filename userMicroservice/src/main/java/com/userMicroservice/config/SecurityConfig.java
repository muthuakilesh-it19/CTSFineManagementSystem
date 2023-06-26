package com.userMicroservice.config;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import com.userMicroservice.model.Role;
import com.userMicroservice.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//import com.userMicroservice.service.UserInfoService;

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

    private static final long EXPIRATION_TIME = 900000; // 15 minutes (in milliseconds)

    
    private static final String SECRET_KEY = "akileshhselika"; // Replace with your own secret key
    
    public String generateJwtToken(User user) {
        // Set the expiration time for the token
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        
        // Build the JWT token with the user information
        String token = Jwts.builder()
                .claim("userId", user.getUserId())
                .claim("roles", roleNames)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return token;
    }
  /*  @Bean
    UserDetailsService userDetailsService() {
		return new UserInfoService();
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/users/{userId}/vehicles","/users/{userId}/vehicles/**","/usermicroservice/user/getUserById{id}","/usermicroservice/register","/usermicroservice/check","/usermicroservice/add","/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**","/usermicroservice/user/**")
				.permitAll()
				.and()
				.authorizeHttpRequests().requestMatchers( "/usermicroservice/violation/**","/usermicroservice/payment/**")
				.authenticated().and().formLogin().and().build();

	}

    @Bean
    AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}
*/
    @Bean
    PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
    }
}