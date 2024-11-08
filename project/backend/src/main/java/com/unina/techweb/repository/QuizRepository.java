package com.unina.techweb.repository;

import com.unina.techweb.entities.Customer;
import com.unina.techweb.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {

    @Query("select q from Quiz q where q.createdby = ?1")
    List<Quiz> findByIdUser(Customer customer);

}