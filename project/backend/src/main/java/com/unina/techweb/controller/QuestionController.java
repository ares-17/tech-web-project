package com.unina.techweb.controller;


import com.unina.techweb.controller.api.QuestionsApi;
import com.unina.techweb.dto.QuestionDto;
import com.unina.techweb.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(questionService.getQuestionsByQuiz(idQuiz));
    }
}
