package com.example.smartproject.controller;

import com.example.smartproject.entities.ContactEntity;
import com.example.smartproject.entities.UserEntity;
import com.example.smartproject.helper.Message;
import com.example.smartproject.smartDao.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private UserRespository userRespository;


    // To Load Home Page
    @RequestMapping("/")
    public String home(Model model)
    {
        model.addAttribute("title", "PSEG Dashboard");
        return "home";
    }

//    About us page
    @RequestMapping("/about")
    public String aboutUs(Model model)
    {
        model.addAttribute("title","About PSEG Dashboard");
        return "aboutus";
    }

    @RequestMapping("/signup")
    public String signup(Model model)
    {
        //title is key and signup is value
        model.addAttribute("title","SignUp Page");
        //        user is key and new UserEntity() is value
        model.addAttribute("userEntity",new UserEntity());
        return "signup";
    }


//    Handler for registering User
    @RequestMapping(value = "/do_register", method=RequestMethod.POST)
//    mapping user (key) to the userEntity using ModelAttribute
//using Model model if user has to be sent
    public String registerUser(@Valid @ModelAttribute("userEntity") UserEntity userEntity,  BindingResult bindingResult, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession httpSession) {

        try {
            if (!agreement) {
                System.out.println("You have not selected terms and conditions");
                throw new Exception("You have not selected terms and conditions");
            }
        if(bindingResult.hasErrors())
        {
//            toString will print error by converting object into String
            System.out.println("Error "+bindingResult.toString());
            model.addAttribute("userEntity",userEntity);
            return "signup";
        }

            userEntity.setRole("ROLE_USER");
            userEntity.setStatus(true);
            userEntity.setImageUrl("default.png");

            System.out.println("Agreement " + agreement);
            System.out.println("user " + userEntity);

//  below save the data in the database
            UserEntity result = this.userRespository.save(userEntity);

            //        below code will keep the saved data in the form
//        model.addAttribute("userEntity",userEntity);
//        below code will store data in the database
//       model.addAttribute("userEntity",result);

//       remove old entries from form and show empty form for new user
            model.addAttribute("userEntity", new UserEntity());
            httpSession.setAttribute("message", new Message("Successfully registered !! ", "alert-success"));
            return "signup";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("userEntity", userEntity);
//      alert-error will come in red color if exception comes
            httpSession.setAttribute("message", new Message("Something went wrong !! " + e.getMessage(), "alert-danger"));
        }
        return "signup";
    }
}
