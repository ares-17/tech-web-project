package com.unina.techweb.repository;

import com.unina.techweb.entities.Answer;
import com.unina.techweb.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {

  @Query("select a from Answer a where a.uidquestion = ?1")
  List<Answer> getAnswerByQuestion(Question question);

}