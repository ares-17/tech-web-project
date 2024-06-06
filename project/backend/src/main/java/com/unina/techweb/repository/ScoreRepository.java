package com.unina.techweb.repository;

import com.unina.techweb.entities.Customer;
import com.unina.techweb.entities.Quiz;
import com.unina.techweb.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ScoreRepository extends JpaRepository<Score, UUID> {

    @Query("select s from Score s where s.quiz = ?1")
    List<Score> findByQuiz(Quiz quiz);

    @Query("select s from Score s where s.customer = ?1")
    List<Score> findByCustomer(Customer customer);
}