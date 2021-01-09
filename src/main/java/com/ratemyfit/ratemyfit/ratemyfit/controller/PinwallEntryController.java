package com.ratemyfit.ratemyfit.controller;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.service.PinwallEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

@Controller
public class PinwallEntryController {

    @Autowired
    private PinwallEntryService pinwallEntryService;

    @RequestMapping("/index")
    public String viewPinwallentryPage(Model model) {
        List<PinwallEntry> listPinwallentry= pinwallEntryService.listAll();
        model.addAttribute("listPinwallentry", listPinwallentry);

        return "index";
    }

    @RequestMapping("/new")
    public String showNewPinwallEntryPage(Model model) {
        PinwallEntry pinwallEntry = new PinwallEntry();
        model.addAttribute("pinwallentry", pinwallEntry);

        return "new_post_view";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePinwallEntry(@ModelAttribute("pinwallEntry") PinwallEntry pinwallEntry) {
        pinwallEntryService.save(pinwallEntry);

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



}
