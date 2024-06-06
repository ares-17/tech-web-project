package com.unina.techweb.service;

import com.unina.techweb.dto.*;
import com.unina.techweb.entities.*;
import com.unina.techweb.repository.CustomerRepository;
import com.unina.techweb.repository.QuizRepository;
import com.unina.techweb.repository.ScoreRepository;
import com.unina.techweb.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final QuizRepository quizRepository;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @Autowired
    ScoreService(ScoreRepository scoreRepository, QuizRepository quizRepository, CustomerRepository customerRepository, CustomerService customerService){
        this.scoreRepository = scoreRepository;
        this.quizRepository = quizRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }


    @Transactional
    public void completeQuiz(QuizResponseDto dto){
        if(dto == null || dto.getId() == null ||
            dto.getQuestions() == null ||
            dto.getQuestions().isEmpty()){
            throw new IllegalArgumentException("Parametri inseriti non validi per completeQuiz");
        }

        Quiz quiz = this.quizRepository.findById(UUID.fromString(dto.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Quiz non trovato"));

        Customer customer = (dto.getIsCustomerAnonymous()) ?
                this.customerService.getOrCreateAnonymousCustomer() :
                this.customerRepository.findById(UUID.fromString(dto.getIdCustomer()))
                    .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        Score score = new Score();
        score.setQuiz(quiz);
        score.setCustomer(customer);
        score.setScore(calculateScore(dto, quiz));
        score.setCompletedat(Calendar.getInstance().toInstant());
        this.scoreRepository.save(score);
    }

    private int calculateScore(QuizResponseDto dto, Quiz entity){
        if(entity.getQuestions().size() != dto.getQuestions().size()){
            throw new IllegalArgumentException("Il numero di domande risposte al quiz non è sufficiente");
        }

        var ordedQuestions = entity.getQuestions().stream()
            .sorted(Comparator.comparing(Question::getId))
                .toList();

        var ordedQuestionResponses = dto.getQuestions().stream()
            .sorted(Comparator.comparing(QuestionResponseDto::getId)).toList();

        int score = 0;
        for(int i = 0; i < ordedQuestions.size(); i++){
            score = isAnswerRight(ordedQuestionResponses.get(i) ,ordedQuestions.get(i));
        }

        return score;
    }

    private int isAnswerRight(QuestionResponseDto response, Question dto){
        return dto.getAnswers().stream()
                .map(Answer::getText)
                .anyMatch(text -> text.equals(response.getAnswer())) ? 1 : 0;
    }

    public List<ScoreDto> getScoresByQuiz(String idQuiz) {
        Quiz quiz = this.quizRepository.findById(UUID.fromString(idQuiz))
                .orElseThrow(() -> new IllegalArgumentException("Quiz non trovato"));

        return this.scoreRepository.findByQuiz(quiz).stream()
                .map(Mapper::mapScoreQuizCustomerToScoreCustomerDto)
                .toList();
    }

    public List<ScoreDto> getScoreByCustomer(String idCustomer) {
        Customer customer = this.customerRepository.findById(UUID.fromString(idCustomer))
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        return this.scoreRepository.findByCustomer(customer).stream()
                .map(Mapper::mapScoreQuizCustomerToScoreCustomerDto)
                .toList();
    }
}
