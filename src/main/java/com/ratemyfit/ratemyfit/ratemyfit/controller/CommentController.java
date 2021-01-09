package com.ratemyfit.ratemyfit.controller;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

import com.ratemyfit.ratemyfit.model.Comment;
import com.ratemyfit.ratemyfit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/index_c")
    public String viewCommentPage(Model model) {
        List<Comment> listComment= commentService.listAll();
        model.addAttribute("listComment", listComment);

        return "index_c";
    }

    @RequestMapping("/new_c")
    public String showNewCommentPage(Model model) {
        Comment comment = new Comment();
        model.addAttribute("comment", comment);

        return "new_post_view_C";
    }


    @RequestMapping(value = "/save_c", method = RequestMethod.POST)
    public String saveComment(@ModelAttribute("comment") Comment comment) {
        commentService.save(comment);

        return "redirect:/";
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



}
