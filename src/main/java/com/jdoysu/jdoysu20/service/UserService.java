package com.jdoysu.jdoysu20.service;

import com.jdoysu.jdoysu20.model.User;
import com.jdoysu.jdoysu20.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordService passwordService;

    public void registerUser(User user){
        String hashedPassword = passwordService.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public boolean loginUser(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (passwordService.verifyPassword(password, user.getPassword())){
                return true;
            }
        }
        return false;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<String> updateUser(Long id, User loginModel) {
        // Extracting the id into an optional
        Optional<User> optionalLoginModel = userRepository.findById(id);
        // If Optional is not empty
        if (optionalLoginModel.isPresent()){

            // Since there is an Optional with the ID, it will get the optional and store into existingLoginModel
            User existingLoginModel = optionalLoginModel.get();

            // Assign new Data into the fields
//            existingLoginModel.setFirstName(loginModel.getFirstName());
//            existingLoginModel.setLastName(loginModel.getLastName());
            existingLoginModel.setEmail(loginModel.getEmail());
            existingLoginModel.setPassword(loginModel.getPassword());

            // Saving updated
            userRepository.save(existingLoginModel);
            return new ResponseEntity<>("Found and Updated", HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>("Couldn't find by ID", HttpStatus.BAD_REQUEST);
        }

    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
