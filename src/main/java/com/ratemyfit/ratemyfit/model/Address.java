package com.ratemyfit.ratemyfit.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;


/**
 * Address.java
 * Purpose: Address Entity - Declare fields of table Address and manages the relationships /foreignkeys to the other tables
 * @author Florian Jäger
 */

@Entity
public class Address {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "street_name", length = 45)
    String streetName;

    @Column(name = "city_name", length = 45)
    String CityName;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
