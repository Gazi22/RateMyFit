package com.ratemyfit.ratemyfit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);





}
