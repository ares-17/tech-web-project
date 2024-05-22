package com.unina.techweb.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.unina.techweb.entities.Scorequizcustomer}
 */
@Value
@AllArgsConstructor
@Getter
@Setter
public class ScorequizcustomerDto implements Serializable {

    String id;

    @NotNull
    String idCustomer;

    @NotNull
    String idQuiz;

    @NotNull
    @Min(0)
    @Positive
    Long score;
}