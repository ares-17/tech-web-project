package com.unina.techweb.repository;

import com.unina.techweb.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    @Query("select q from Question q where q.uidquiz = ?1")
    List<Question> findByIdQuiz(String uuid);
}