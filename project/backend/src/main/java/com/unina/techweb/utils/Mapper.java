package com.unina.techweb.utils;

import com.unina.techweb.dto.*;
import com.unina.techweb.entities.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Mapper {

    public static Answer mapAnswerDtoToAnswer(AnswerDto answerDto, Question question){
        var answer = new Answer();
        answer.setId(answerDto.getId());
        answer.setText(answerDto.getText());
        answer.setIscorrect(answerDto.getIsCorrect());
        answer.setUidquestion(question);
        return answer;
    }

    public static Question mapQuestionDtoToQuestion(QuestionDto questionDto, Quiz quiz){
        var question = new Question();
        question.setId(questionDto.getId());
        question.setUidquiz(quiz);
        question.setTitle(questionDto.getTitle());

        var answers = new HashSet<>(questionDto.getAnswers().stream()
                .map(a -> Mapper.mapAnswerDtoToAnswer(a, question)).toList());

        question.setAnswers(answers);
        return question;
    }

    public static QuestionDto mapQuestionToDTO(Question question, List<Answer> answers, UUID idQuiz){
        List<AnswerDto> answersDto = (answers != null) ?
                answers.stream().map(a -> Mapper.mapAnswerToDto(a, idQuiz)).toList() :
                List.of();

        return new QuestionDto(
                question.getId(),
                question.getTitle(),
                question.getUidquiz().getId(),
                answersDto
        );
    }

    private static AnswerDto mapAnswerToDto(Answer answer, UUID idQuiz) {
        return new AnswerDto(
                answer.getId(),
                answer.getText(),
                answer.getIscorrect(),
                answer.getUidquestion().getId(),
                idQuiz
        );
    }

    public static QuizDto mapQuizToQuizDTO(Quiz quiz, List<QuestionDto> questions){
        return new QuizDto(
                quiz.getId(),
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getCreatedat(),
                quiz.getCreatedby().getId(),
                quiz.getMaxerrors(),
                quiz.getIsopen(),
                questions
        );
    }

    public static Quiz mapQuizDTOToQuiz(QuizDto quizDto, Customer customer) {
        var quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setDescription(quizDto.getDescription());
        quiz.setCreatedat(Instant.now());
        quiz.setMaxerrors(quizDto.getMaxErrors());
        quiz.setIsopen(quizDto.getIsOpen());
        quiz.setCreatedby(customer);
        quiz.setId(quizDto.getId());

        Set<Question> questions = new HashSet<>(quizDto.getQuestions().stream()
                .map(q -> Mapper.mapQuestionDtoToQuestion(q, quiz)).toList());

        quiz.setQuestions(questions);
        return quiz;
    }

    public static CustomerDto mapCustomerToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getUsername(),
                customer.getIslogged(),
                customer.getIsanonymous()
        );
    }

    public static ScoreDto mapScoreQuizCustomerToScoreCustomerDto(Score entity){
        return new ScoreDto(
                entity.getCustomer().getId().toString(),
                entity.getCustomer().getUsername(),
                entity.getCustomer().getIslogged(),
                entity.getCustomer().getIsanonymous(),
                entity.getQuiz().getId().toString(),
                entity.getQuiz().getTitle(),
                entity.getQuiz().getDescription(),
                entity.getQuiz().getCreatedat(),
                entity.getQuiz().getMaxerrors(),
                entity.getQuiz().getIsopen(),
                entity.getScore(),
                entity.getCompletedat()
        );
    }
}
