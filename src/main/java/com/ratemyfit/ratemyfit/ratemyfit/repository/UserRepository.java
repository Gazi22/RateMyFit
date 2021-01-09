package com.ratemyfit.ratemyfit.repository;

import com.ratemyfit.ratemyfit.model.Role;
import com.ratemyfit.ratemyfit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//https://www.codejava.net/frameworks/spring-boot/user-registration-and-login-tutorial

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    /*@Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserbyEmail(@Param("email") String email);*/

    List findAllByRole(Role role);


}
