package com.revature.MKPG.beans.Repositories;

import com.revature.MKPG.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c WHERE c.email =:email")
    Optional<Customer> findByEmail(
            @Param("email") String email);
}
