package com.jdoysu.jdoysu20.controller;

import com.jdoysu.jdoysu20.model.User;
import com.jdoysu.jdoysu20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;



    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
        if (userService.loginUser(email, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @GetMapping("/secured")
    public String secured(){
        return "Hello, Secured";
    }

    //Read
    @GetMapping("/getUsers")
    public List<User> allUsers(){
        return userService.allUsers();
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody User loginModel){
        return userService.updateUser(id,loginModel);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public void DeletingUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }



}
