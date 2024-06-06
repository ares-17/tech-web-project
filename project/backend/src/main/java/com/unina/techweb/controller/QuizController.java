package com.unina.techweb.controller;

import com.unina.techweb.controller.api.QuizApi;
import com.unina.techweb.dto.QuizDto;
import com.unina.techweb.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class QuizController implements QuizApi {

    private final QuizService quizService;

    @Autowired
    QuizController(QuizService service){
        this.quizService = service;
    }

    @Override
    public ResponseEntity<QuizDto> getQuizById(String uidQuiz) {
        return ResponseEntity.ok(quizService.getQuiz(uidQuiz));
    }

    @Override
    public ResponseEntity<QuizDto> createQuiz(QuizDto quizDto) {
        var savedQuizDto = quizService.createQuiz(quizDto);
        return ResponseEntity.ok(savedQuizDto);
    }
}
