package com.ratemyfit.ratemyfit.controller;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

import com.ratemyfit.ratemyfit.model.Comment;
import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.service.CommentService;
import com.ratemyfit.ratemyfit.service.CustomUserDetails;
import com.ratemyfit.ratemyfit.service.PinwallEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PinwallEntryService pinwallEntryService;

    @RequestMapping("/index_c")
    public String viewCommentPage(Model model) {
        Long id = null;
        List<Comment> listComment= commentService.listAll(id);
        model.addAttribute("listComment", listComment);

        return "index_c";
    }

    @RequestMapping("/new_c")
    public String showNewCommentPage(Model model) {
        Comment comment = new Comment();
        model.addAttribute("comment", comment);

        return "new_post_view_C";
    }




    @RequestMapping(value = "/save_c/{id}", method = RequestMethod.POST)
    public String saveComment(Comment comment,@PathVariable(name="id") Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();



        User user = customUserDetails.getUser();
        comment.setCreatedDate(Calendar.getInstance().toInstant());
        comment.setPinwallEntry(pinwallEntryService.getPinwallentryForID(id));
        comment.setUser(user);
        commentService.save(comment);
        return "ratings_comments";
    }

   @RequestMapping("/edit_c/{id}")
    public ModelAndView showEditCommentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_Comment");
        Comment comment = commentService.get(id);
        mav.addObject("comment", comment);

        return mav;
    }


    @RequestMapping("/delete_c/{id}")
    public String deleteComment(@PathVariable(name = "id") int id) {
        commentService.delete(id);
        return "redirect:/";
    }


    @RequestMapping("/find_comments")
    public String findComments(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = customUserDetails.getUser();

        List<Comment> listComment = commentService.listAllCurrentUser(user.getId());


        model.addAttribute("listComment", listComment);

        return "ratings_comments";
    }


}
