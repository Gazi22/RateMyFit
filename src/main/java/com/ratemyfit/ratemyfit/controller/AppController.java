package com.ratemyfit.ratemyfit.controller;

import com.ratemyfit.ratemyfit.model.Address;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.repository.AddressRepository;
import com.ratemyfit.ratemyfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**

 * AppController.java
 * Purpose: User Controller - Managing views and Mapping (POST, GET) of the User entity
 * @author Florian Jäger
 */
@Controller
public class AppController {


/**
     * Declare UserRepository as Autowired (Injection to get access to class methods etc.)
     */
    @Autowired
    private UserRepository userRepo;
    
/**
     * Declare AddressRepository as Autowired (Injection to get access to class methods etc.)
     */
    @Autowired
    private AddressRepository addressRepository;

    /**
     * Return to homepage
     */
    @GetMapping("")
    public String viewHomePage() {
        return "homepage";
    }

/**
     * Create new User and address + open the register view
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("address", new Address());

        return "register";
    }

    
    /**
     * Process Register - save the entered data in the user / address into the corresponding repository
     */
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



    /**
     * //Currently not in use//
     * View that returns all registered users (Intended for the admin to manage users.)
     */
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }


     /**
     * Return to all_fits view
     */
    @GetMapping("/all_fits")
    public String viewAllFitsPage() {return "all_fits_old";}

     /**
     * Return to ratings_comments view
     */
    @GetMapping("/ratings_comments")
    public String viewMyRatingsandComments() {return "ratings_comments";}

     /**
     * Return to profile view
     */
    @GetMapping("/profile")
    public String viewSettings() {return "profile";}

     /**
     * Return to contact_us view
     */
    @GetMapping("/contact_us")
    public String viewContactUs() {return "contact_us";}

     /**
     * Return to about_us view
     */
    @GetMapping("/about_us")
    public String viewAboutUs() {return "about_us";}

     /**
     * Return to /homepage 2 -> to call homepage by using "/homepage"
     */
    @GetMapping("/homepage")
    public String viewHomepage() {
        return "homepage";
    }

     /**
     * Return username to e.g. thymleaf field
     */
   @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication){
        return authentication.getName();
   }


 /**
     * NOT USED
     */
    @GetMapping("/giveAuthority")
    public String giveAuthority(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);


        return "users";
    }

    
     /**
     * Login process - returns the login view
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

}
