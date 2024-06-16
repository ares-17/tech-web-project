package com.unina.techweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unina.techweb.validators.NoHtml;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.Objects;

/**
 * AnswerDto
 */

@AllArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-05T18:03:26.214085516+02:00[Europe/Rome]")
public class AnswerDto {

  @JsonProperty("id")
  @NoHtml
  private String id;

  @JsonProperty("text")
  @NoHtml
  private String text;

  @JsonProperty("isCorrect")
  private Boolean isCorrect;

  @JsonProperty("idQuestion")
  @NoHtml
  private String idQuestion;

  @JsonProperty("idQuiz")
  @NoHtml
  private String idQuiz;

  public AnswerDto id(String id) {
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

  public AnswerDto text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Get text
   * @return text
  */
  @NotNull 
  @Schema(name = "text", required = true)
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public AnswerDto isCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
    return this;
  }

  /**
   * Get isCorrect
   * @return isCorrect
  */
  @NotNull 
  @Schema(name = "isCorrect", required = true)
  public Boolean getIsCorrect() {
    return isCorrect;
  }

  public void setIsCorrect(Boolean isCorrect) {
    this.isCorrect = isCorrect;
  }

  public AnswerDto idQuestion(String idQuestion) {
    this.idQuestion = idQuestion;
    return this;
  }

  /**
   * Get idQuestion
   * @return idQuestion
  */
  
  @Schema(name = "idQuestion", required = false)
  public String getIdQuestion() {
    return idQuestion;
  }

  public void setIdQuestion(String idQuestion) {
    this.idQuestion = idQuestion;
  }

  public AnswerDto idQuiz(String idQuiz) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnswerDto answerDto = (AnswerDto) o;
    return Objects.equals(this.id, answerDto.id) &&
        Objects.equals(this.text, answerDto.text) &&
        Objects.equals(this.isCorrect, answerDto.isCorrect) &&
        Objects.equals(this.idQuestion, answerDto.idQuestion) &&
        Objects.equals(this.idQuiz, answerDto.idQuiz);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, text, isCorrect, idQuestion, idQuiz);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnswerDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    isCorrect: ").append(toIndentedString(isCorrect)).append("\n");
    sb.append("    idQuestion: ").append(toIndentedString(idQuestion)).append("\n");
    sb.append("    idQuiz: ").append(toIndentedString(idQuiz)).append("\n");
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

