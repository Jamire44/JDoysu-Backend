package com.jdoysu.jdoysu20.controller;

import com.jdoysu.jdoysu20.model.LoginModel;
import com.jdoysu.jdoysu20.repository.LoginRepository;
import com.jdoysu.jdoysu20.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;


    //Create
    @PostMapping("/add")
    public void add(@RequestBody LoginModel loginModel){
        loginService.addUser(loginModel);
    }

    //Read
    @GetMapping("/getUsers")
    public List<LoginModel> allUsers(){
        return loginService.allUsers();
    }

    //Update
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody LoginModel loginModel){
        return loginService.updateUser(id,loginModel);
    }

    //Delete
    @DeleteMapping("/delete/{id}")
    public void DeletingUser(@PathVariable Long id){
        loginService.deleteUserById(id);
    }



}
