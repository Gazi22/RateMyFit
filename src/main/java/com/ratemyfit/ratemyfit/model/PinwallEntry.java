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
    private Long id;

    @Column(name = "wall_entry")
    private String wallEntry;

    @Column(name = "post_On_Date")
    private Calendar postOn;

    @Column (name = "picture", length = 45)
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY)
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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAuthorName (){

        return author.getUsername();
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Transient
    public String getOutfitImagePath(){
        if (picture==null||id == null) return null;

        return"/outfits/"+ picture;
    }



}

