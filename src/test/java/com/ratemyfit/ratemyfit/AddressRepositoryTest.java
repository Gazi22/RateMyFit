package com.ratemyfit.ratemyfit;

import com.ratemyfit.ratemyfit.model.Address;
import com.ratemyfit.ratemyfit.model.User;
import com.ratemyfit.ratemyfit.repository.AddressRepository;
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
public class AddressRepositoryTest {


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepo;


    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void testCreateAddress(){
       Address address = new Address();

        User user1 = userRepo.findByEmail("test3@test.com");
        address.setId(user1.getId());
        address.setStreetName("Teststreet3");
        address.setCityName("Testy3");
        address.setUser(user1);

        userRepo.save(user1);
        addressRepository.save(address);


    }
}
