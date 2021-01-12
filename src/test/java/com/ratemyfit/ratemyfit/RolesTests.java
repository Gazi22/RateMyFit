package com.ratemyfit.ratemyfit;

import com.ratemyfit.ratemyfit.model.Role;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.repository.UserRepository;
import com.ratemyfit.ratemyfit.service.CustomUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RolesTests {

CustomUserDetails customUserDetails;

    @Autowired
    private UserRepository userRepo;


    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testCreateAddress(){

       User user1 = userRepo.findByEmail("test3@test.com");
        Set<Role> roles = user1.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        userRepo.save(user1);


    }
}
