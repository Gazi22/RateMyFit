package com.ratemyfit.ratemyfit.controller;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

import com.ratemyfit.ratemyfit.model.Comment;
import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.service.CommentService;
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

/**
 * PinwallEntryController.java
 * Purpose: PinwallEntry Controller - Managing views and Mapping (POST, GET) of the Pinwallentry entity
 * @author Florian Jäger
 */

@Controller
public class PinwallEntryController {

    /**
     * Declare CustomUserDetailsService as Autowired (Injection to get access to class methods etc.)
     */
    @Autowired
    CustomUserDetailsService customUserDetailsService;

     /**
     * Declare PinwallEntryService as Autowired (Injection to get access to class methods etc.)
     */
    @Autowired
    private PinwallEntryService pinwallEntryService;
    
     /**
     * Declare CommentService as Autowired (Injection to get access to class methods etc.)
     */
    @Autowired
    private CommentService commentService;

    /**
     * List all posts and return the index_p (all_fits) view
     */
    @RequestMapping("/index_p")
    public String viewPinwallentryPage(Model model) {
        Long id = null;
        List<PinwallEntry> listPinwallentry = pinwallEntryService.listAll(id);
        model.addAttribute("listPinwallentry", listPinwallentry);

        return "index_p";
    }

    /**
     * List posts of the cuirrent user and return the view my_fit
     */
    @RequestMapping("/my_fit")
    public String viewMyFit(Model model) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = customUserDetails.getUser();

        List<PinwallEntry> listPinwallentry = pinwallEntryService.listAllCurrentUserPosts(user.getId());


        model.addAttribute("listPinwallentry", listPinwallentry);

        return "my_fit";
    }

    /**
     * return the view upload_your_fit to create a post
     */
    @RequestMapping("/new")
    public String showNewPinwallEntryPage(Model model) {
        PinwallEntry pinwallEntry = new PinwallEntry();
        model.addAttribute("pinwallentry", pinwallEntry);

        return "upload_your_fit";
    }

    /**
     * Save a post with multipartfile for the picture of the outfit
     * Adapted from //https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial
     */
    
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
        return "my_fit";


    }

    /**
     * Currently not used
     * Makes it possible to add posts
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPinwallEntryPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_PinwallEntry");
        PinwallEntry pinwallEntry = pinwallEntryService.get(id);
        mav.addObject("pinwallEntry", pinwallEntry);

        return mav;
    }

    /**
     * Let's the user delete his own posts
     */
    @RequestMapping("/delete/{id}")
    public String deletePinwallEntry(@PathVariable(name = "id") int id) {
        pinwallEntryService.delete(id);
        return "redirect:/";
    }

    
     /**
     * Returns the post depending on the ID- return view rate_my_fit_post
     */
    @RequestMapping("/find_post/{id}")
    public String findPost(Model model,Comment comment, @PathVariable(name="id") Long id) {

        comment = new Comment();

        List<PinwallEntry> listPinwallentry = pinwallEntryService.listAll(id);
        List<Comment> listComment= commentService.listAll(id);

        model.addAttribute("id", id);
        model.addAttribute("listPinwallentry", listPinwallentry);
        model.addAttribute("listComment", listComment);
        model.addAttribute("comment", comment);
        return "rate_my_fit_post";
    }



    }

