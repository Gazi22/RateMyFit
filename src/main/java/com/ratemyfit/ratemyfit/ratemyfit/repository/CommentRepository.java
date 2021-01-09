package com.ratemyfit.ratemyfit.repository;


import com.ratemyfit.ratemyfit.model.Comment;
import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /*List<Comment> findByPost(PinwallEntry pinwallEntry);

    List<Comment> findAllByUser(User user);*/
}
