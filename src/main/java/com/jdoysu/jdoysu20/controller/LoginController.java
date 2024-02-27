package com.jdoysu.jdoysu20.controller;

import com.jdoysu.jdoysu20.model.Customer;
import com.jdoysu.jdoysu20.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final CustomerRepository repository;

    public LoginController(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedCustomer = null;
        ResponseEntity response = null;
        try {
            savedCustomer = repository.save(customer);
            if (savedCustomer.getId() > 0){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given User Details are successfully registered");
            }
        }catch (Exception e){
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Issue occurred because of : " + e.getMessage());
        }
        return response;
    }

}
