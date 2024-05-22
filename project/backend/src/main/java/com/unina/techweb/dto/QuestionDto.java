package com.unina.techweb.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.unina.techweb.entities.Question}
 */
@Value
@Setter
@Getter
@AllArgsConstructor
public class QuestionDto implements Serializable {

    UUID id;

    @NotNull
    @Size(max = 255)
    String title;

    UUID idQuiz;

    List<AnswerDto> answers;
}