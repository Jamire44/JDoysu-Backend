package com.jdoysu.jdoysu20.service;

import com.jdoysu.jdoysu20.model.LoginModel;
import com.jdoysu.jdoysu20.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void updateUser(Long id, LoginModel loginModel) {
        if (loginRepository.existsById(id)){
            loginRepository.deleteById(id);
            loginRepository.save(loginModel);
        }


    }
}
