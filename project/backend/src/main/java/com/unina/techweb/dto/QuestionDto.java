package com.unina.techweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QuestionDto
 */

@AllArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-05T18:03:26.214085516+02:00[Europe/Rome]")
public class QuestionDto {

  @JsonProperty("title")
  private String title;

  @JsonProperty("id")
  private String id;

  @JsonProperty("idQuiz")
  private String idQuiz;

  @JsonProperty("answers")
  @Valid
  private List<AnswerDto> answers = new ArrayList<>();

  public QuestionDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @NotNull 
  @Schema(name = "title", required = true)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public QuestionDto id(String id) {
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

  public QuestionDto idQuiz(String idQuiz) {
    this.idQuiz = idQuiz;
    return this;
  }

  /**
   * Get idQuiz
   * @return idQuiz
  */
  
  @Schema(name = "idQuiz", required = false)
  public String getIdQuiz() {
    return idQuiz;
  }

  public void setIdQuiz(String idQuiz) {
    this.idQuiz = idQuiz;
  }

  public QuestionDto answers(List<AnswerDto> answers) {
    this.answers = answers;
    return this;
  }

  public QuestionDto addAnswersItem(AnswerDto answersItem) {
    this.answers.add(answersItem);
    return this;
  }

  /**
   * Get answers
   * @return answers
  */
  @NotNull @Valid 
  @Schema(name = "answers", required = true)
  public List<AnswerDto> getAnswers() {
    return answers;
  }

  public void setAnswers(List<AnswerDto> answers) {
    this.answers = answers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuestionDto questionDto = (QuestionDto) o;
    return Objects.equals(this.title, questionDto.title) &&
        Objects.equals(this.id, questionDto.id) &&
        Objects.equals(this.idQuiz, questionDto.idQuiz) &&
        Objects.equals(this.answers, questionDto.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, id, idQuiz, answers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuestionDto {\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idQuiz: ").append(toIndentedString(idQuiz)).append("\n");
    sb.append("    answers: ").append(toIndentedString(answers)).append("\n");
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

