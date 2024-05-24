package com.unina.techweb.service;

import com.unina.techweb.dto.ScoreDto;
import com.unina.techweb.entities.Customer;
import com.unina.techweb.entities.Quiz;
import com.unina.techweb.entities.Score;
import com.unina.techweb.repository.CustomerRepository;
import com.unina.techweb.repository.QuizRepository;
import com.unina.techweb.repository.ScoreRepository;
import com.unina.techweb.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final QuizRepository quizRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    ScoreService(ScoreRepository scoreRepository, QuizRepository quizRepository, CustomerRepository customerRepository){
        this.scoreRepository = scoreRepository;
        this.quizRepository = quizRepository;
        this.customerRepository = customerRepository;
    }


    @Transactional
    public void completeQuiz(ScoreDto dto){
        if(dto == null || dto.getQuizId() == null ||
                dto.getCustomerId() == null ||
                dto.getScore() < 0){
            throw new IllegalArgumentException("parametri inseriti non validi per completeQuiz");
        }

        Quiz quiz = this.quizRepository.findById(UUID.fromString(dto.getQuizId()))
                .orElseThrow(() -> new IllegalArgumentException("Quiz non trovato"));
        Customer customer = this.customerRepository.findById(UUID.fromString(dto.getCustomerId()))
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));


        Score score = new Score();
        score.setQuiz(quiz);
        score.setCustomer(customer);
        score.setScore(dto.getScore());
        score.setCompletedat(Calendar.getInstance().toInstant());
        this.scoreRepository.save(score);
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
