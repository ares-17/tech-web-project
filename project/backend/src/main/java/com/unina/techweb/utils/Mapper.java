package com.unina.techweb.utils;

import com.unina.techweb.dto.*;
import com.unina.techweb.entities.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.*;

public class Mapper {

    public static UUID safeUUIDFromString(String str){
        return (str == null) ? null : UUID.fromString(str);
    }

    public static String stringFromUUID(UUID uuid){
        return (uuid == null) ? null : uuid.toString();
    }

    public static Comparator<UUID> compareUUID(){
        return (a, b) -> {
            var stringA = stringFromUUID(a);
            var stringB = stringFromUUID(b);
            return stringA.compareTo(stringB);
        };
    }

    public static Answer mapAnswerDtoToAnswer(AnswerDto answerDto, Question question){
        var answer = new Answer();
        answer.setId(safeUUIDFromString(answerDto.getId()));
        answer.setText(answerDto.getText());
        answer.setIscorrect(answerDto.getIsCorrect());
        answer.setUidquestion(question);
        return answer;
    }

    public static Question mapQuestionDtoToQuestion(QuestionDto questionDto, Quiz quiz){
        var question = new Question();
        question.setId(safeUUIDFromString(questionDto.getId()));
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
                question.getTitle(),
                stringFromUUID(question.getId()),
                question.getUidquiz().getId().toString(),
                answersDto
        );
    }

    private static AnswerDto mapAnswerToDto(Answer answer, UUID idQuiz) {
        return new AnswerDto(
                stringFromUUID(answer.getId()),
                answer.getText(),
                answer.getIscorrect(),
                stringFromUUID(answer.getUidquestion().getId()),
                idQuiz.toString()
        );
    }

    public static QuizDto mapQuizToQuizDTO(Quiz quiz, List<QuestionDto> questions){
        return new QuizDto(
                stringFromUUID(quiz.getId()),
                quiz.getTitle(),
                quiz.getDescription(),
                quiz.getCreatedat().atOffset(ZoneOffset.UTC),
                stringFromUUID(quiz.getCreatedby().getId()),
                Long.parseLong(quiz.getMaxerrors().toString()),
                questions
        );
    }

    public static Quiz mapQuizDTOToQuiz(QuizDto quizDto, Customer customer) {
        var quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setDescription(quizDto.getDescription());
        quiz.setCreatedat(Instant.now());
        quiz.setMaxerrors(Integer.parseInt(quizDto.getMaxErrors().toString()));
        quiz.setCreatedby(customer);
        quiz.setId(safeUUIDFromString(quizDto.getId()));

        Set<Question> questions = new HashSet<>(quizDto.getQuestions().stream()
                .map(q -> Mapper.mapQuestionDtoToQuestion(q, quiz)).toList());

        quiz.setQuestions(questions);
        return quiz;
    }

    public static CustomerDto mapCustomerToCustomerDto(Customer customer) {
        return new CustomerDto(
                stringFromUUID(customer.getId()),
                customer.getUsername(),
                customer.getPassword()
        );
    }

    public static Customer mapCustomerDtoToCustomer(CustomerDto customer) {
        var entity = new Customer();
        entity.setUsername(customer.getUsername());
        entity.setId(safeUUIDFromString(customer.getId()));
        entity.setPassword(customer.getPassword());
        return entity;
    }

    public static ScoreDto mapScoreQuizCustomerToScoreCustomerDto(Score entity){
        return new ScoreDto(
                stringFromUUID(entity.getCustomer().getId()),
                entity.getCustomer().getUsername(),
                stringFromUUID(entity.getQuiz().getId()),
                entity.getQuiz().getTitle(),
                entity.getQuiz().getDescription(),
                entity.getQuiz().getCreatedat().toString(),
                entity.getQuiz().getMaxerrors(),
                entity.getCompletedat().atOffset(ZoneOffset.UTC),
                Long.parseLong(entity.getScore().toString())
        );
    }
}
