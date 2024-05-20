package com.unina.techweb.service;

import com.unina.techweb.model.CompleteQuizDTO;
import com.unina.techweb.model.Question;
import com.unina.techweb.model.Quiz;
import com.unina.techweb.model.QuizQuestionsDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service
public class QuizService {

    public ResponseEntity<Void> completeQuiz(CompleteQuizDTO completeQuizDTO) {
        throw new UnsupportedOperationException();
    }

    public ResponseEntity<List<Question>> getPlayersByQuiz(String uidQuiz) {
        throw new UnsupportedOperationException();
    }

    public ResponseEntity<Quiz> createQuiz(@RequestBody @Valid QuizQuestionsDTO quizQuestionsDTO) {
        throw new UnsupportedOperationException();
    }

    public ResponseEntity<Quiz> getQuiz(String uidQuiz) {
        throw new UnsupportedOperationException();
    }
}
