package com.example.smartproject.controller;

import com.example.smartproject.entities.ContactEntity;
import com.example.smartproject.entities.UserEntity;
import com.example.smartproject.smartDao.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    private UserRespository userRespository;
//Home Page
    @RequestMapping("/")
    public String home(Model model)
    {
        model.addAttribute("title", "PSEG Dashboard");
        return "home";
    }

//    About us
    @RequestMapping("/about")
    public String aboutUs(Model model)
    {
        model.addAttribute("title","About PSEG Dashboard");
        return "aboutus";
    }

    @RequestMapping("/signup")
    public String signup(Model model)
    {
//        title is key and signup is value
        model.addAttribute("title","SignUp Page");
        //        user is key and new UserEntity() is value
        model.addAttribute("userEntity",new UserEntity());
        return "signup";
    }


//    Handler for registering User
    @RequestMapping(value = "/do_register", method=RequestMethod.POST)
//    mapping user (key) to the userEntity using ModelAttribute
//using Model model if user has to be sent
    public  String registerUser(@ModelAttribute("userEntity") UserEntity userEntity, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model)
    {
        System.out.println("Agreement "+agreement);
        System.out.println("user "+userEntity);
        return "signup";
    }
}
