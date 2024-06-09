package com.unina.techweb.service;

import com.unina.techweb.dto.QuestionResponseDto;
import com.unina.techweb.dto.QuizResponseDto;
import com.unina.techweb.dto.ScoreDto;
import com.unina.techweb.entities.*;
import com.unina.techweb.exceptions.NotFoundException;
import com.unina.techweb.repository.CustomerRepository;
import com.unina.techweb.repository.QuizRepository;
import com.unina.techweb.repository.ScoreRepository;
import com.unina.techweb.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;

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

    private Customer getCustomerQuizResponseDto(final QuizResponseDto dto){
        if(dto.getNonAuthenticableUsername() == null &&
                dto.getIsCustomerAnonymous() == null &&
                dto.getIdCustomer() == null){
            throw new IllegalArgumentException("Parametri inseriti non validi per completeQuiz");
        }
        if(dto.getIdCustomer() != null && !dto.getIdCustomer().isEmpty()){
            return this.customerRepository.findById(UUID.fromString(dto.getIdCustomer()))
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));
        }
        if(dto.getNonAuthenticableUsername() != null){
            return this.customerService.createNonAuthenticableUser(dto.getNonAuthenticableUsername());
        }
        return this.customerService.createNonAuthenticableUser("Anonymous");
    }

    @Transactional
    public void completeQuiz(final QuizResponseDto dto){
        if(dto == null || dto.getId() == null ||
            dto.getQuestions() == null ||
            dto.getQuestions().isEmpty()){
            throw new IllegalArgumentException("Parametri inseriti non validi per completeQuiz");
        }

        Quiz quiz = this.quizRepository.findById(UUID.fromString(dto.getId()))
                .orElseThrow(() -> new NotFoundException("Quiz non trovato"));

        Customer customer = getCustomerQuizResponseDto(dto);

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

        List<Question> questionsList = new ArrayList<>(entity.getQuestions().stream().toList());
        List<QuestionResponseDto> dtoQuestionsList = new ArrayList<>(dto.getQuestions());

        questionsList.sort(Comparator.comparing(q -> Mapper.stringFromUUID(q.getId())));
        dtoQuestionsList.sort(Comparator.comparing(QuestionResponseDto::getId));

        return IntStream.range(0, questionsList.size())
            .map(i -> isAnswerRight(dtoQuestionsList.get(i), questionsList.get(i)))
            .sum();
    }

    private int isAnswerRight(QuestionResponseDto response, Question dto){
        var answersList = dto.getAnswers().stream().toList();
        if(answersList.size() == 1){
            return answersList.get(0).getText().equals(response.getAnswer()) ? 1 : 0;
        }

        return answersList.stream()
            .filter(Answer::getIscorrect)
            .mapToInt(a -> a.getText().equals(response.getAnswer()) ? 1 : 0)
            .sum();
    }

    public List<ScoreDto> getScoresByQuiz(String idQuiz) {
        Quiz quiz = this.quizRepository.findById(UUID.fromString(idQuiz))
                .orElseThrow(() -> new NotFoundException("Quiz non trovato"));

        return this.scoreRepository.findByQuiz(quiz).stream()
                .map(Mapper::mapScoreQuizCustomerToScoreCustomerDto)
                .toList();
    }

    public List<ScoreDto> getScoreByCustomer(String idCustomer) {
        Customer customer = this.customerRepository.findById(UUID.fromString(idCustomer))
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        return this.scoreRepository.findByCustomer(customer).stream()
                .map(Mapper::mapScoreQuizCustomerToScoreCustomerDto)
                .toList();
    }
}
