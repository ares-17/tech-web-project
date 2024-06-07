package com.unina.techweb.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unina.techweb.dto.QuestionDto;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * QuizDto
 */
@AllArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-07T16:05:10.821464250+02:00[Europe/Rome]")
public class QuizDto {

  @JsonProperty("id")
  private String id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("description")
  private String description;

  @JsonProperty("createdAt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @JsonProperty("createdBy")
  private String createdBy;

  @JsonProperty("maxErrors")
  private Long maxErrors;

  @JsonProperty("questions")
  @Valid
  private List<QuestionDto> questions = new ArrayList<>();

  public QuizDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public QuizDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @NotNull 
  @Schema(name = "title", example = "A beatiful Quiz", required = true)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public QuizDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  
  @Schema(name = "description", example = "Description of Quiz", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public QuizDto createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @Valid 
  @Schema(name = "createdAt", required = false)
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public QuizDto createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

  /**
   * Get createdBy
   * @return createdBy
  */
  
  @Schema(name = "createdBy", required = false)
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public QuizDto maxErrors(Long maxErrors) {
    this.maxErrors = maxErrors;
    return this;
  }

  /**
   * Get maxErrors
   * @return maxErrors
  */
  @NotNull 
  @Schema(name = "maxErrors", example = "3", required = true)
  public Long getMaxErrors() {
    return maxErrors;
  }

  public void setMaxErrors(Long maxErrors) {
    this.maxErrors = maxErrors;
  }

  public QuizDto questions(List<QuestionDto> questions) {
    this.questions = questions;
    return this;
  }

  public QuizDto addQuestionsItem(QuestionDto questionsItem) {
    this.questions.add(questionsItem);
    return this;
  }

  /**
   * Get questions
   * @return questions
  */
  @NotNull @Valid 
  @Schema(name = "questions", required = true)
  public List<QuestionDto> getQuestions() {
    return questions;
  }

  public void setQuestions(List<QuestionDto> questions) {
    this.questions = questions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuizDto quizDto = (QuizDto) o;
    return Objects.equals(this.id, quizDto.id) &&
        Objects.equals(this.title, quizDto.title) &&
        Objects.equals(this.description, quizDto.description) &&
        Objects.equals(this.createdAt, quizDto.createdAt) &&
        Objects.equals(this.createdBy, quizDto.createdBy) &&
        Objects.equals(this.maxErrors, quizDto.maxErrors) &&
        Objects.equals(this.questions, quizDto.questions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, createdAt, createdBy, maxErrors, questions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuizDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    maxErrors: ").append(toIndentedString(maxErrors)).append("\n");
    sb.append("    questions: ").append(toIndentedString(questions)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

