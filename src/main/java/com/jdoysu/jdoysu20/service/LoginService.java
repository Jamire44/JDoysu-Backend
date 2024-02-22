package com.jdoysu.jdoysu20.service;

import com.jdoysu.jdoysu20.model.LoginModel;
import com.jdoysu.jdoysu20.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {


    @Autowired
    LoginRepository loginRepository;


    public void addUser(LoginModel loginModel) {
        loginRepository.save(loginModel);
    }

    public List<LoginModel> allUsers() {
        return loginRepository.findAll();
    }

    public ResponseEntity<String> updateUser(Long id, LoginModel loginModel) {
        // Extracting the id into an optional
        Optional<LoginModel> optionalLoginModel = loginRepository.findById(id);
        // If Optional is not empty
        if (optionalLoginModel.isPresent()){

            // Since there is an Optional with the ID, it will get the optional and store into existingLoginModel
            LoginModel existingLoginModel = optionalLoginModel.get();

            // Assign new Data into the fields
            existingLoginModel.setFirstName(loginModel.getFirstName());
            existingLoginModel.setLastName(loginModel.getLastName());
            existingLoginModel.setEmail(loginModel.getEmail());
            existingLoginModel.setPassword(loginModel.getPassword());

            // Saving updated
            loginRepository.save(existingLoginModel);
            return new ResponseEntity<>("Found and Updated", HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>("Couldn't find by ID", HttpStatus.BAD_REQUEST);
        }

    }

    public void deleteUserById(Long id) {
        loginRepository.deleteById(id);
    }
}
