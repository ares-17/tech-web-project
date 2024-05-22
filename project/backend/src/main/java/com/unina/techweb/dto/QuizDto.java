package com.unina.techweb.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.unina.techweb.entities.Quiz}
 */
@Value
@Setter
@Getter
@AllArgsConstructor
public class QuizDto implements Serializable {

    UUID id;

    @NotNull
    @Size(max = 255)
    @NotEmpty
    @NotBlank
    String title;

    @Size(max = 255)
    String description;

    Instant createdAt;

    @NotNull
    UUID createdById;

    @NotNull
    @Min(0)
    @PositiveOrZero
    Integer maxErrors;

    Boolean isOpen;

    List<QuestionDto> questions;
}