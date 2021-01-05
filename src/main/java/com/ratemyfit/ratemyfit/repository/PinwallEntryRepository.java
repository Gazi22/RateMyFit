package com.ratemyfit.ratemyfit.repository;

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


    /*List<PinwallEntry> findbyuser(User user);*/
}
