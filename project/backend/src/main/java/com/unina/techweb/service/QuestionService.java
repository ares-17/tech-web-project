package com.unina.techweb.service;

import com.unina.techweb.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class QuestionService {

    public ResponseEntity<List<Question>> getQuestionsByQuiz(String uidQuiz) {
        throw new UnsupportedOperationException();
    }
}
