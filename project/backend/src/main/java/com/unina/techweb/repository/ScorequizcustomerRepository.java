package com.unina.techweb.repository;

import com.unina.techweb.entities.Quiz;
import com.unina.techweb.entities.Scorequizcustomer;
import com.unina.techweb.entities.ScorequizcustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScorequizcustomerRepository extends JpaRepository<Scorequizcustomer, ScorequizcustomerId> {

    @Query("select s from Scorequizcustomer s where s.uidquiz = ?1")
    List<Scorequizcustomer> findByQuiz(Quiz quiz);
}