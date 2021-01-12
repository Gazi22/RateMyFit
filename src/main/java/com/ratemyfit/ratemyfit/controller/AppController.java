package com.ratemyfit.ratemyfit.controller;

import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.repository.UserRepository;
import com.ratemyfit.ratemyfit.service.CustomUserDetails;
import com.ratemyfit.ratemyfit.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.Authenticator;
import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial

@Controller
public class AppController {



    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "orgMainPage";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid @ModelAttribute("user") User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);


        /*try {*/
            userRepo.save(user);

    /*    } catch (DataIntegrityViolationException e) {
        System.out.println("User already exist");

        return "signup_form";
    }*/
        return "register_success";}




    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping("/myFit")
    public String viewMyFitPage() {return "orgMyFit";}

    @GetMapping("/myRatingsandComments")
    public String viewMyRatingsandComments() {return "orgMyRatingsandComments";}

    @GetMapping("/settings")
    public String viewSettings() {return "orgSettings";}

    @GetMapping("/mainPage")
    public String viewMainPage() {
        return "orgMainPage";
    }

   @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication){
        return authentication.getName();
   }



    @GetMapping("/giveAuthority")
    public String giveAuthority(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);


        return "users";
    }



}
