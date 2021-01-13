package com.ratemyfit.ratemyfit.model;


import com.ratemyfit.ratemyfit.model.User;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.Authentication;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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


    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "pinwall_id")
    protected Set<Comment> comment=new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "pinwall_id")
    protected Set<Rating> rating=new HashSet<>();



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

    public String getWallEntry() {
        return wallEntry;
    }

    public void setWallEntry(String wallEntry) {
        this.wallEntry = wallEntry;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public List<String> getCommentText(){

            return comment.stream().map(Comment::getCommentText).collect(Collectors.toList());

    }

    public List<String> getCommentUser(){

        return comment.stream().map(Comment::getUserName).collect(Collectors.toList());

    }

    public String getBoth(){

         List<String> list1= comment.stream().map(Comment::getUserName).collect(Collectors.toList());
         List<String> list2 =comment.stream().map(Comment::getCommentText).collect(Collectors.toList());

         List<String> newList = new ArrayList<>();

        
        for (int i=0;i<list1.size();){
            newList.add(list1.get(i));
            i++;
            for (int j=i-1;j<i;j++){
                newList.add(list2.get(j));

            }

        }

        String usernameAndComment = "";
        String delimiter = ",";
        for (String comment : newList) {
            usernameAndComment += usernameAndComment.equals("") ? comment : delimiter + comment;
        }

        return usernameAndComment;
    }

    public Set<Rating> getRating() {
        return rating;
    }

    public void setRating(Set<Rating> rating) {
        this.rating = rating;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }



}

