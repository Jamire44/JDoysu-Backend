package com.jdoysu.jdoysu20.controller;

import com.jdoysu.jdoysu20.model.Customer;
import com.jdoysu.jdoysu20.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(CustomerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        ResponseEntity<String> response;
        try {
            // Check if the password is not null
            if (customer.getPwd() == null) {
                // Handle the case where the password is null
                response = ResponseEntity
                        .badRequest()
                        .body("Password cannot be null");
            } else {
                // Encode the password
                String hashedPassword = passwordEncoder.encode(customer.getPwd());
                customer.setPwd(hashedPassword);
                // Save the customer
                Customer savedCustomer = repository.save(customer);
                if (savedCustomer.getId() > 0) {
                    response = ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body("Given User Details are successfully registered");
                } else {
                    response = ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Failed to register user");
                }
            }
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Issue occurred because of : " + e.getMessage());
        }
        return response;
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Customer customer){
        List<Customer> existingCustomers = repository.findByEmail(customer.getEmail());
        if (!existingCustomers.isEmpty()) {
            Customer existingCustomer = existingCustomers.get(0);
            if (passwordEncoder.matches(customer.getPwd(), existingCustomer.getPwd())) {
                return ResponseEntity.ok("Login Successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }




}


