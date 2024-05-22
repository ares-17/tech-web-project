package com.unina.techweb.service;

import com.unina.techweb.dto.CustomerDto;
import com.unina.techweb.dto.QuizDto;
import com.unina.techweb.dto.ScorequizcustomerDto;
import com.unina.techweb.entities.Customer;
import com.unina.techweb.entities.Quiz;
import com.unina.techweb.entities.Scorequizcustomer;
import com.unina.techweb.repository.CustomerRepository;
import com.unina.techweb.repository.QuizRepository;
import com.unina.techweb.repository.ScorequizcustomerRepository;
import com.unina.techweb.utils.Mapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ScorequizcustomerRepository scorequizcustomerRepository;

    @Autowired
    QuizService(
            QuizRepository quizRepository,
            CustomerRepository customerRepository,
            QuestionService questionService,
            ScorequizcustomerRepository scorequizcustomerRepository
    ){
        this.quizRepository = quizRepository;
        this.customerRepository = customerRepository;
        this.questionService = questionService;
        this.scorequizcustomerRepository = scorequizcustomerRepository;
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
        return this.scorequizcustomerRepository.findByQuiz(quiz).stream()
            .map(Scorequizcustomer::getUidcustomer)
            .map(Mapper::mapCustomerToCustomerDto)
            .toList();
    }

    @Transactional
    public void completeQuiz(ScorequizcustomerDto dto){
        if(dto == null || dto.getIdQuiz() == null ||
            dto.getIdCustomer() == null ||
            dto.getScore() < 0){
            throw new IllegalArgumentException("parametri inseriti non validi per completeQuiz");
        }

        Quiz quiz = this.quizRepository.findById(UUID.fromString(dto.getIdQuiz()))
            .orElseThrow(() -> new IllegalArgumentException("Quiz non trovato"));
        Customer customer = this.customerRepository.findById(UUID.fromString(dto.getIdCustomer()))
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        Scorequizcustomer scorequizcustomer = new Scorequizcustomer();
        scorequizcustomer.setUidquiz(quiz);
        scorequizcustomer.setUidcustomer(customer);
        scorequizcustomer.setScore(dto.getScore());
        this.scorequizcustomerRepository.save(scorequizcustomer);
    }
}
