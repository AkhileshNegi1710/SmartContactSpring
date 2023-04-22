package com.example.smartproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    //   http://localhost:8080/user/index to load the page
    @RequestMapping("/index")
    public String dashboard()
    {

        return "normalUser/user_dashboard";
    }

}
