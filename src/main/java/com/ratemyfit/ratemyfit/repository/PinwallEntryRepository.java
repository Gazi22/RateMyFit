package com.ratemyfit.ratemyfit.repository;

import com.ratemyfit.ratemyfit.model.Comment;
import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinwallEntryRepository extends JpaRepository<PinwallEntry, Long> {


    @Query("SELECT u FROM PinwallEntry u WHERE u.id = ?1")
    public PinwallEntry findbyentryid(Long id);

 /*   @Query("SELECT u FROM PinwallEntry u WHERE u.id = ?1")
    public List <PinwallEntry> findEntryByID(Long id);*/

    @Query("SELECT p FROM PinwallEntry p WHERE p.id = ?1")
    public List<PinwallEntry> search(Long id);

    @Query("SELECT p FROM PinwallEntry p WHERE p.author.id = ?1")
    public List<PinwallEntry> listAllCurrentUser(Long id);

    /*List<PinwallEntry> findbyuser(User user);*/
}
