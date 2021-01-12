package com.ratemyfit.ratemyfit;

import static org.assertj.core.api.Assertions.assertThat;

import com.ratemyfit.ratemyfit.model.Comment;
import com.ratemyfit.ratemyfit.model.PinwallEntry;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.repository.CommentRepository;
import com.ratemyfit.ratemyfit.repository.PinwallEntryRepository;
import com.ratemyfit.ratemyfit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Calendar;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("kek999@gmail.com");
        user.setPassword("Florian2020");
        user.setFirstName("Florian");
        user.setLastName("Jäger");

        User savedUser = userRepo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }


}
