package com.unina.techweb.repository;

import com.unina.techweb.entities.Customer;
import com.unina.techweb.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("select c from Customer c where ")
    List<Customer> findByQuiz(Quiz quiz);
}