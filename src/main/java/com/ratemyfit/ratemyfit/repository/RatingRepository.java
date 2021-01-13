package com.ratemyfit.ratemyfit.repository;


import com.ratemyfit.ratemyfit.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    /*List<Comment> findByPost(PinwallEntry pinwallEntry);

    List<Comment> findAllByUser(User user);*/

    @Query("SELECT r FROM Rating r WHERE r.pinwallEntry.id = ?1")
    public List<Rating> search(Long id);

    @Query("SELECT r FROM Rating r WHERE r.user.id = ?1")
    public List<Rating> listAllCurrentUser(Long id);

}
