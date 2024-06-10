package com.unina.techweb.service;

import com.unina.techweb.dto.QuestionDto;
import com.unina.techweb.entities.Quiz;
import com.unina.techweb.exceptions.TWNotFoundException;
import com.unina.techweb.repository.AnswerRepository;
import com.unina.techweb.repository.QuizRepository;
import com.unina.techweb.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class QuestionService {

    private final AnswerRepository answerRepository;
    private final QuizRepository quizRepository;

    @Autowired
    QuestionService(QuizRepository quizRepository,
                    AnswerRepository answerRepository) {
        this.quizRepository = quizRepository;
        this.answerRepository = answerRepository;
    }

    public List<QuestionDto> getQuestionsByQuiz(String uidQuiz) {
        Quiz quiz = quizRepository.findById(UUID.fromString(uidQuiz))
                .orElseThrow(() -> new TWNotFoundException(uidQuiz));

        var questionsWithAnswers = quiz.getQuestions().stream()
                .peek(q -> q.setAnswers(new HashSet<>(answerRepository.getAnswerByQuestion(q)))).toList();

        return questionsWithAnswers.stream()
                .map(q -> Mapper.mapQuestionToDTO(q, q.getAnswers().stream().toList(), UUID.fromString(uidQuiz)))
                .toList();
    }
}
