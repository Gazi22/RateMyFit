package com.ratemyfit.ratemyfit.repository;

import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
   /* Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(PinwallEntry pinwallEntry, User currentUser);*/
}
