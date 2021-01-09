package com.ratemyfit.ratemyfit.model;


import com.ratemyfit.ratemyfit.model.User;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.Authentication;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "wall")
public class PinwallEntry {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Column(name = "wall_entry")
    private String wallEntry;

    @Column(name = "post_On_Date")
    private Calendar postOn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "author")
    private User author;


 /*   @Column(name="rating")
    private float rating;


    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }*/



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWallentry() {
        return wallEntry;
    }

    public void setWallentry(String wallEntry) {
        this.wallEntry = wallEntry;
    }

    public Calendar getPostOn() {
        return postOn;
    }

    public void setPostOn(Calendar postOn) {
        this.postOn = postOn;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

   }

