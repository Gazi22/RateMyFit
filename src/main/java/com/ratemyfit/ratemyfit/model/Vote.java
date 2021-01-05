package com.ratemyfit.ratemyfit.model;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private int rating;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_Id", referencedColumnName = "id")
    private PinwallEntry pinwallEntry;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;


}
