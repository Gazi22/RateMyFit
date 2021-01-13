package com.ratemyfit.ratemyfit.service;

import com.ratemyfit.ratemyfit.model.Rating;
import com.ratemyfit.ratemyfit.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

@Service
@Transactional
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;



    public List<Rating> listAll(Long id) {
        if (id != null){
            return ratingRepository.search(id);
        }
        return ratingRepository.findAll();
    }

    public List<Rating> listAllCurrentUser(Long id) {
        if (id != null){
            return ratingRepository.listAllCurrentUser(id);
        }
        return ratingRepository.findAll();
    }

    public Rating save(Rating rating) {
        ratingRepository.save(rating);
        return rating;
    }
    public Rating get(long id) {
        return ratingRepository.findById(id).get();
    }

    public void delete(long id) {
        ratingRepository.deleteById(id);
    }
}
