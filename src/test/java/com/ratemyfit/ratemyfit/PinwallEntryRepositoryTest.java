package com.ratemyfit.ratemyfit;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PinwallEntryRepositoryTest {


    @Autowired
    private PinwallEntryRepository pinwallRepo;


    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testCreatePinwallEntry() {
        PinwallEntry pinwallEntry = new PinwallEntry();
        User user1 = new User();
        user1.setEmail("kek34@gmail.com");
        user1.setPassword("Floria4n2020");
        user1.setFirstName("Florian");
        user1.setLastName("Jäger");
        pinwallEntry.setWallentry("Moin Leuoode");
        pinwallEntry.setPostOn(Calendar.getInstance());
        pinwallEntry.setAuthor(user1);


        pinwallRepo.save(pinwallEntry);
    }

}