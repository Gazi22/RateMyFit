package com.ratemyfit.ratemyfit.model;
import javax.persistence.*;


/**
 * Role.java
 * Purpose: Role Entity - Declare fields of table Role and manages the relationships /foreignkeys to the other tables
 * @author Florian Jäger
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }


}
