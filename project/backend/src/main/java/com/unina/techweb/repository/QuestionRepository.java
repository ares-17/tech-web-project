package com.unina.techweb.repository;

import com.unina.techweb.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

}