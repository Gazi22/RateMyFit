package com.ratemyfit.ratemyfit.repository;

import com.ratemyfit.ratemyfit.model.Address;
import com.ratemyfit.ratemyfit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {



}
