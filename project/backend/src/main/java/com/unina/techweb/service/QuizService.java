package com.unina.techweb.service;

import com.unina.techweb.dto.CustomerDto;
import com.unina.techweb.dto.QuizDto;
import com.unina.techweb.entities.Customer;
import com.unina.techweb.entities.Quiz;
import com.unina.techweb.repository.CustomerRepository;
import com.unina.techweb.repository.QuizRepository;
import com.unina.techweb.utils.Mapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final CustomerRepository customerRepository;
    private final QuestionService questionService;

    @Autowired
    QuizService(
            QuizRepository quizRepository,
            CustomerRepository customerRepository,
            QuestionService questionService
    ){
        this.quizRepository = quizRepository;
        this.customerRepository = customerRepository;
        this.questionService = questionService;
    }

    @Transactional
    public QuizDto createQuiz(@RequestBody @Valid QuizDto quizDto) {
        Customer customer = this.customerRepository.getById(quizDto.getCreatedById());
        Quiz quiz = Mapper.mapQuizDTOToQuiz(quizDto, customer);
        var id = this.quizRepository.save(quiz).getId();

        return new QuizDto(
                id,
                quizDto.getTitle(),
                quizDto.getDescription(),
                quizDto.getCreatedAt(),
                quizDto.getCreatedById(),
                quizDto.getMaxErrors(),
                quiz.getIsopen(),
                quizDto.getQuestions()
        );
    }

    public QuizDto getQuiz(String uidQuiz) {
        var quiz = this.quizRepository.getById(UUID.fromString(uidQuiz));
        var questions = this.questionService.getQuestionsByQuiz(uidQuiz);
        return Mapper.mapQuizToQuizDTO(quiz, questions);
    }

    public List<CustomerDto> getUsersByQuiz(String uidQuiz) {
        var quiz = this.quizRepository.findById(UUID.fromString(uidQuiz)).orElseThrow();
        var customers = this.customerRepository.findByQuiz(quiz);
    }
}
