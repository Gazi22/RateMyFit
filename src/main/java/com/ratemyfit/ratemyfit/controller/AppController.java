package com.ratemyfit.ratemyfit.controller;

import com.ratemyfit.ratemyfit.model.Address;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.repository.AddressRepository;
import com.ratemyfit.ratemyfit.repository.UserRepository;
import com.ratemyfit.ratemyfit.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial

@Controller
public class AppController {



    @Autowired
    private UserRepository userRepo;



    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "homepage";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());

        return "register";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid @ModelAttribute("user") User user,@ModelAttribute("address") Address address) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);


        /*try {*/

            user.setAddress(address);
            address.setUser(user);
            userRepo.save(user);
            addressRepository.save(address);


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





    @GetMapping("/ratings_comments")
    public String viewMyRatingsandComments() {return "ratings_comments";}

    @RequestMapping("/profile")
    public String viewProfile(Model model) {

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        Address address =user.getAddress();
        model.addAttribute("user", user);
        model.addAttribute("address", address);

        return "profile";}



    @GetMapping("/contact_us")
    public String viewContactUs() {return "contact_us";}

    @GetMapping("/about_us")
    public String viewAboutUs() {return "about_us";}

    @GetMapping("/homepage")
    public String viewHomepage() {
        return "homepage";
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

}
