package com.jdoysu.jdoysu20.config;

import com.jdoysu.jdoysu20.model.Customer;
import com.jdoysu.jdoysu20.repository.CustomerRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class JdoysuEmailPwdProvider implements AuthenticationProvider {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    public JdoysuEmailPwdProvider(CustomerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        List<Customer> customers = repository.findByEmail(username);
        if (customers.size() > 0){
            if (passwordEncoder.matches(pwd, customers.get(0).getPwd())){
                return new UsernamePasswordAuthenticationToken(username,pwd);
            } else {
                throw new BadCredentialsException("Invalid Password");
            }
        }else {
            throw new BadCredentialsException("No User With Them Credentials");
        }
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
