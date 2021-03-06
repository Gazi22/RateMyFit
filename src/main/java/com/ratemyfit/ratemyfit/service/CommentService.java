package com.ratemyfit.ratemyfit.service;

import com.ratemyfit.ratemyfit.model.Comment;
import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.repository.CommentRepository;
import com.ratemyfit.ratemyfit.repository.PinwallEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;



    public List<Comment> listAll(Long id) {
        if (id != null){
            return commentRepository.search(id);
        }
        return commentRepository.findAll();
    }

    public List<Comment> listAllCurrentUser(Long id) {
        if (id != null){
            return commentRepository.listAllCurrentUser(id);
        }
        return commentRepository.findAll();
    }

    public Comment save(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }
    public Comment get(long id) {
        return commentRepository.findById(id).get();
    }

    public void delete(long id) {
        commentRepository.deleteById(id);
    }
}
