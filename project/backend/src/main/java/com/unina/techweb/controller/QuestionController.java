package com.unina.techweb.controller;


import com.unina.techweb.controller.api.QuestionsApi;
import com.unina.techweb.dto.QuestionDto;
import com.unina.techweb.exceptions.NotFoundException;
import com.unina.techweb.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class QuestionController implements QuestionsApi {

    private final QuestionService questionService;

    @Autowired
    QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @Override
    public ResponseEntity<List<QuestionDto>> getQuestionsByQuiz(String idQuiz) {
        try{
            return ResponseEntity.ok(questionService.getQuestionsByQuiz(idQuiz));
        } catch(NotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
