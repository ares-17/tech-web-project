package com.unina.techweb.controller;

import com.unina.techweb.controller.api.QuizApi;
import com.unina.techweb.model.CompleteQuizDTO;
import com.unina.techweb.model.Question;
import com.unina.techweb.model.Quiz;
import com.unina.techweb.model.QuizQuestionsDTO;
import com.unina.techweb.service.QuizService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class QuizController implements QuizApi {

    private final QuizService quizService;

    @Autowired
    QuizController(QuizService service){
        this.quizService = service;
    }

    @Override
    public ResponseEntity<Void> completeQuiz(CompleteQuizDTO completeQuizDTO) {
        return this.quizService.completeQuiz(completeQuizDTO);
    }

    @Override
    public ResponseEntity<List<Question>> getPlayersByQuiz(String uidQuiz) {
        return this.quizService.getPlayersByQuiz(uidQuiz);
    }

    @Override
    public ResponseEntity<Quiz> createQuiz(@RequestBody @Valid QuizQuestionsDTO quizQuestionsDTO) {
        return this.quizService.createQuiz(quizQuestionsDTO);
    }

    @Override
    public ResponseEntity<Quiz> getQuiz(String uidQuiz) {
        return this.quizService.getQuiz(uidQuiz);
    }
}
