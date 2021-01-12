package com.ratemyfit.ratemyfit.service;

import java.util.List;

import com.ratemyfit.ratemyfit.model.Comment;
import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.repository.PinwallEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-crud-example-with-spring-mvc-spring-data-jpa-thymeleaf-hibernate-mysql

@Service
@Transactional
public class PinwallEntryService {

    @Autowired
    private PinwallEntryRepository pinwallEntryRepository;


    public List<PinwallEntry> listAll(Long id) {
        if (id != null){
            return pinwallEntryRepository.search(id);
        }
        return pinwallEntryRepository.findAll();
    }

    public List<PinwallEntry> listAllCurrentUserPosts(Long id) {
        if (id != null){
            return pinwallEntryRepository.listAllCurrentUser(id);
        }
        return pinwallEntryRepository.findAll();
    }


    public PinwallEntry getPinwallentryForID(Long id) {

        return pinwallEntryRepository.findbyentryid(id);
    }

    public PinwallEntry save(PinwallEntry pinwallEntry) {
        pinwallEntryRepository.save(pinwallEntry);
        return pinwallEntry;
    }

    public PinwallEntry get(long id) {
        return pinwallEntryRepository.findById(id).get();
    }

    public void delete(long id) {
        pinwallEntryRepository.deleteById(id);
    }
}
