package com.unina.techweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QuizResponseDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-06T14:26:27.070404138+02:00[Europe/Rome]")
public class QuizResponseDto {

  @JsonProperty("questions")
  @Valid
  private List<QuestionResponseDto> questions = null;

  @JsonProperty("id")
  private String id;

  @JsonProperty("isCustomerAnonymous")
  private Boolean isCustomerAnonymous;

  @JsonProperty("idCustomer")
  private String idCustomer;

  public QuizResponseDto questions(List<QuestionResponseDto> questions) {
    this.questions = questions;
    return this;
  }

  public QuizResponseDto addQuestionsItem(QuestionResponseDto questionsItem) {
    if (this.questions == null) {
      this.questions = new ArrayList<>();
    }
    this.questions.add(questionsItem);
    return this;
  }

  /**
   * Get questions
   * @return questions
  */
  @Valid 
  @Schema(name = "questions", required = false)
  public List<QuestionResponseDto> getQuestions() {
    return questions;
  }

  public void setQuestions(List<QuestionResponseDto> questions) {
    this.questions = questions;
  }

  public QuizResponseDto id(String id) {
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

  public QuizResponseDto isCustomerAnonymous(Boolean isCustomerAnonymous) {
    this.isCustomerAnonymous = isCustomerAnonymous;
    return this;
  }

  /**
   * Get isCustomerAnonymous
   * @return isCustomerAnonymous
  */
  
  @Schema(name = "isCustomerAnonymous", required = false)
  public Boolean getIsCustomerAnonymous() {
    return isCustomerAnonymous;
  }

  public void setIsCustomerAnonymous(Boolean isCustomerAnonymous) {
    this.isCustomerAnonymous = isCustomerAnonymous;
  }

  public QuizResponseDto idCustomer(String idCustomer) {
    this.idCustomer = idCustomer;
    return this;
  }

  /**
   * Get idCustomer
   * @return idCustomer
  */
  
  @Schema(name = "idCustomer", required = false)
  public String getIdCustomer() {
    return idCustomer;
  }

  public void setIdCustomer(String idCustomer) {
    this.idCustomer = idCustomer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuizResponseDto quizResponseDto = (QuizResponseDto) o;
    return Objects.equals(this.questions, quizResponseDto.questions) &&
        Objects.equals(this.id, quizResponseDto.id) &&
        Objects.equals(this.isCustomerAnonymous, quizResponseDto.isCustomerAnonymous) &&
        Objects.equals(this.idCustomer, quizResponseDto.idCustomer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(questions, id, isCustomerAnonymous, idCustomer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuizResponseDto {\n");
    sb.append("    questions: ").append(toIndentedString(questions)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    isCustomerAnonymous: ").append(toIndentedString(isCustomerAnonymous)).append("\n");
    sb.append("    idCustomer: ").append(toIndentedString(idCustomer)).append("\n");
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
