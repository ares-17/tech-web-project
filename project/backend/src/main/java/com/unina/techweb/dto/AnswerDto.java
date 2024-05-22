package com.unina.techweb.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.unina.techweb.entities.Answer}
 */
@Value
@Setter
@Getter
public class AnswerDto implements Serializable {

    UUID id;

    @NotNull
    @Size(max = 255)
    String text;

    @NotNull
    Boolean isCorrect;

    UUID idQuestion;

    UUID idQuiz;
}