package com.ratemyfit.ratemyfit.controller;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

import com.ratemyfit.ratemyfit.model.Rating;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.service.CustomUserDetails;
import com.ratemyfit.ratemyfit.service.PinwallEntryService;
import com.ratemyfit.ratemyfit.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private PinwallEntryService pinwallEntryService;

   /* @RequestMapping("/index_c")
    public String viewRatingPage(Model model) {
        Long id = null;
        List<Rating> listRating= ratingService.listAll(id);
        model.addAttribute("listRating", listRating);

        return "index_c";
    }

    @RequestMapping("/new_c")
    public String showNewRatingPage(Model model) {
        Rating rating = new Rating();
        model.addAttribute("rating", rating);

        return "new_post_view_C";
    }*/




    @RequestMapping(value = "/save_r/{id}", method = RequestMethod.POST)
    public String saveRating(Rating rating,@PathVariable(name="id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();



        User user = customUserDetails.getUser();
        rating.setPinwallEntry(pinwallEntryService.getPinwallentryForID(id));
        rating.setUser(user);
        ratingService.save(rating);
        return "ratings_comments";
    }

/*   @RequestMapping("/edit_r/{id}")
    public ModelAndView showEditRatingPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_Rating");
        Rating rating = ratingService.get(id);
        mav.addObject("rating", rating);

        return mav;
    }*/


    @RequestMapping("/delete_r/{id}")
    public String deleteRating(@PathVariable(name = "id") int id) {
        ratingService.delete(id);
        return "redirect:/";
    }


    @RequestMapping("/find_rating")
    public String findRatings(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = customUserDetails.getUser();

        List<Rating> listRating = ratingService.listAllCurrentUser(user.getId());


        model.addAttribute("listRating", listRating);

        return "ratings_comments";
    }


}
