package com.ratemyfit.ratemyfit.controller;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.repository.PinwallEntryRepository;
import com.ratemyfit.ratemyfit.service.CustomUserDetails;
import com.ratemyfit.ratemyfit.service.CustomUserDetailsService;
import com.ratemyfit.ratemyfit.service.PinwallEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

@Controller
public class PinwallEntryController {

    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PinwallEntryService pinwallEntryService;



    @RequestMapping("/index_p")
    public String viewPinwallentryPage(Model model) {
        Long id = null;
        List<PinwallEntry> listPinwallentry = pinwallEntryService.listAll(id);
        model.addAttribute("listPinwallentry", listPinwallentry);

        return "index_p";
    }

    @RequestMapping("/new")
    public String showNewPinwallEntryPage(Model model) {
        PinwallEntry pinwallEntry = new PinwallEntry();
        model.addAttribute("pinwallentry", pinwallEntry);

        return "new_post_view";
    }

    //https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePinwallEntry(PinwallEntry pinwallEntry, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {




        String returnValue = "";
        String folder = "./outfits/";
        byte[] bytes = imageFile.getBytes();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User author = customUserDetails.getUser();
        pinwallEntry.setPostOn(Calendar.getInstance());
        pinwallEntry.setPicture(imageFile.getOriginalFilename());
        pinwallEntry.setAuthor(author);
        pinwallEntryService.save(pinwallEntry);

        try {
            Path path = Paths.get(folder + imageFile.getOriginalFilename());
            Files.write(path, bytes);




        } catch (Exception e) {
            e.printStackTrace();


            returnValue = "error";
        }
        return "redirect:/";


    }


    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPinwallEntryPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_PinwallEntry");
        PinwallEntry pinwallEntry = pinwallEntryService.get(id);
        mav.addObject("pinwallEntry", pinwallEntry);

        return mav;
    }


    @RequestMapping("/delete/{id}")
    public String deletePinwallEntry(@PathVariable(name = "id") int id) {
        pinwallEntryService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/findPost")
    public String findPost(Model model, @Param(value="id") Long id) {



        List<PinwallEntry> listPinwallentry = pinwallEntryService.listAll(id);


        model.addAttribute("id", id);
        model.addAttribute("listPinwallentry", listPinwallentry);

        return "show_postPage";
    }

    }

