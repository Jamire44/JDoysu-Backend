package com.jdoysu.jdoysu20.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public String contact(){
        return "Will get from DB";
    }

}
