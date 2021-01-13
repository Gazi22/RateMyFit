package com.ratemyfit.ratemyfit.model;


import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Comment.java
 * Purpose: Comment Entity - Declare fields of table Address and manages the relationships /foreignkeys to the other tables
 * @author Florian Jäger
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String commentText;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "pinwall_id", referencedColumnName = "id")
    private PinwallEntry pinwallEntry;

    @Column
    private Instant post_on_date;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public PinwallEntry getPinwallEntry() {
        return pinwallEntry;
    }

    public void setPinwallEntry(PinwallEntry pinwallEntry) {
        this.pinwallEntry = pinwallEntry;
    }

    public Instant getCreatedDate() {
        return post_on_date;
    }

    public void setCreatedDate(Instant createdDate) {
        this.post_on_date = createdDate;
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



}
