package com.ratemyfit.ratemyfit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
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

       // if(result.hasErrors()){
       //     return "signup_form";
      //  }
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);


        try {
            userRepo.save(new User());

        } catch (DataIntegrityViolationException e) {
        System.out.println("User already exist");

        if (bindingResult.hasErrors()){
            return "signup_form";
        }
            return "signup_form";

    }
        return "register_success";}






    //     if (user != null) {
                //ERROR HERE
           // }

           // if(result.hasErrors()){
           //     return "signup_form";
          //  }

           // ra.addFlashAttribute("kek", "Username/Email already exists.");
         //   return "signup_form";
      //  }





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


}
