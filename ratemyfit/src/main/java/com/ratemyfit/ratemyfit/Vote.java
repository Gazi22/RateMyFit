package com.ratemyfit.ratemyfit;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Entity

public class Vote {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;
    private VoteType voteType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private PinwallEntry pinwallEntry;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}
