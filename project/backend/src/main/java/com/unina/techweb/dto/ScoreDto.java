package com.unina.techweb.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.unina.techweb.entities.Score}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
public class ScoreDto implements Serializable {

    @NotNull
    String customerId;
    
    String customerUsername;
    
    Boolean customerIslogged;
    
    Boolean customerIsanonymous;

    @NotNull
    String quizId;
    
    String quizTitle;
    
    String quizDescription;
    
    Instant quizCreatedat;
    
    Integer quizMaxerrors;
    
    Boolean quizIsopen;
    
    @NotNull
    Integer score;

    Instant completedat;
}