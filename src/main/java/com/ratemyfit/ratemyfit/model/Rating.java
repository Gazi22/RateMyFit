package com.ratemyfit.ratemyfit.model;


import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private int rating;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "pinwall_id", referencedColumnName = "id")
    private PinwallEntry pinwallEntry;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public PinwallEntry getPinwallEntry() {
        return pinwallEntry;
    }

    public void setPinwallEntry(PinwallEntry pinwallEntry) {
        this.pinwallEntry = pinwallEntry;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName (){
        return user.getUsername();
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAverageRating (){
        String avgRatingText="";
        List<Integer> ratingList =pinwallEntry.getRating().stream().map(Rating::getRating).collect(Collectors.toList());
        int listSize = ratingList.size();
        float avrgRating=ratingList.stream().reduce(0,Integer::sum)/ (float)listSize;
        avgRatingText=String.valueOf(avrgRating)+" "+ "AVERAGE RATING";
        return avgRatingText;
    }
}
