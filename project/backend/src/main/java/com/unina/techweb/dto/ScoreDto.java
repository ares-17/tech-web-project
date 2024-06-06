package com.unina.techweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Objects;


/**
 * ScoreDto
 */
@AllArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-05T18:03:26.214085516+02:00[Europe/Rome]")
public class ScoreDto {

  @JsonProperty("customerId")
  private String customerId;

  @JsonProperty("customerUsername")
  private String customerUsername;

  @JsonProperty("customerIslogged")
  private Boolean customerIslogged;

  @JsonProperty("customerIsanonymous")
  private Boolean customerIsanonymous;

  @JsonProperty("quizId")
  private String quizId;

  @JsonProperty("quizTitle")
  private String quizTitle;

  @JsonProperty("quizDescription")
  private String quizDescription;

  @JsonProperty("quizCreatedat")
  private String quizCreatedat;

  @JsonProperty("quizMaxerrors")
  private Integer quizMaxerrors;

  @JsonProperty("quizIsopen")
  private Boolean quizIsopen;

  @JsonProperty("completedaAt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime completedaAt;

  @JsonProperty("score")
  private Long score;

  public ScoreDto customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
   */

  @Schema(name = "customerId", required = false)
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public ScoreDto customerUsername(String customerUsername) {
    this.customerUsername = customerUsername;
    return this;
  }

  /**
   * Get customerUsername
   * @return customerUsername
   */

  @Schema(name = "customerUsername", required = false)
  public String getCustomerUsername() {
    return customerUsername;
  }

  public void setCustomerUsername(String customerUsername) {
    this.customerUsername = customerUsername;
  }

  public ScoreDto customerIslogged(Boolean customerIslogged) {
    this.customerIslogged = customerIslogged;
    return this;
  }

  /**
   * Get customerIslogged
   * @return customerIslogged
   */

  @Schema(name = "customerIslogged", required = false)
  public Boolean getCustomerIslogged() {
    return customerIslogged;
  }

  public void setCustomerIslogged(Boolean customerIslogged) {
    this.customerIslogged = customerIslogged;
  }

  public ScoreDto customerIsanonymous(Boolean customerIsanonymous) {
    this.customerIsanonymous = customerIsanonymous;
    return this;
  }

  /**
   * Get customerIsanonymous
   * @return customerIsanonymous
   */

  @Schema(name = "customerIsanonymous", required = false)
  public Boolean getCustomerIsanonymous() {
    return customerIsanonymous;
  }

  public void setCustomerIsanonymous(Boolean customerIsanonymous) {
    this.customerIsanonymous = customerIsanonymous;
  }

  public ScoreDto quizId(String quizId) {
    this.quizId = quizId;
    return this;
  }

  /**
   * Get quizId
   * @return quizId
   */

  @Schema(name = "quizId", required = false)
  public String getQuizId() {
    return quizId;
  }

  public void setQuizId(String quizId) {
    this.quizId = quizId;
  }

  public ScoreDto quizTitle(String quizTitle) {
    this.quizTitle = quizTitle;
    return this;
  }

  /**
   * Get quizTitle
   * @return quizTitle
   */

  @Schema(name = "quizTitle", required = false)
  public String getQuizTitle() {
    return quizTitle;
  }

  public void setQuizTitle(String quizTitle) {
    this.quizTitle = quizTitle;
  }

  public ScoreDto quizDescription(String quizDescription) {
    this.quizDescription = quizDescription;
    return this;
  }

  /**
   * Get quizDescription
   * @return quizDescription
   */

  @Schema(name = "quizDescription", required = false)
  public String getQuizDescription() {
    return quizDescription;
  }

  public void setQuizDescription(String quizDescription) {
    this.quizDescription = quizDescription;
  }

  public ScoreDto quizCreatedat(String quizCreatedat) {
    this.quizCreatedat = quizCreatedat;
    return this;
  }

  /**
   * Get quizCreatedat
   * @return quizCreatedat
   */

  @Schema(name = "quizCreatedat", required = false)
  public String getQuizCreatedat() {
    return quizCreatedat;
  }

  public void setQuizCreatedat(String quizCreatedat) {
    this.quizCreatedat = quizCreatedat;
  }

  public ScoreDto quizMaxerrors(Integer quizMaxerrors) {
    this.quizMaxerrors = quizMaxerrors;
    return this;
  }

  /**
   * Get quizMaxerrors
   * @return quizMaxerrors
   */

  @Schema(name = "quizMaxerrors", required = false)
  public Integer getQuizMaxerrors() {
    return quizMaxerrors;
  }

  public void setQuizMaxerrors(Integer quizMaxerrors) {
    this.quizMaxerrors = quizMaxerrors;
  }

  public ScoreDto quizIsopen(Boolean quizIsopen) {
    this.quizIsopen = quizIsopen;
    return this;
  }

  /**
   * Get quizIsopen
   * @return quizIsopen
   */

  @Schema(name = "quizIsopen", required = false)
  public Boolean getQuizIsopen() {
    return quizIsopen;
  }

  public void setQuizIsopen(Boolean quizIsopen) {
    this.quizIsopen = quizIsopen;
  }

  public ScoreDto completedaAt(OffsetDateTime completedaAt) {
    this.completedaAt = completedaAt;
    return this;
  }

  /**
   * Get completedaAt
   * @return completedaAt
   */
  @Valid
  @Schema(name = "completedaAt", required = false)
  public OffsetDateTime getCompletedaAt() {
    return completedaAt;
  }

  public void setCompletedaAt(OffsetDateTime completedaAt) {
    this.completedaAt = completedaAt;
  }

  public ScoreDto score(Long score) {
    this.score = score;
    return this;
  }

  /**
   * Get score
   * @return score
   */
  @NotNull
  @Schema(name = "score", example = "3", required = true)
  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScoreDto scoreDto = (ScoreDto) o;
    return Objects.equals(this.customerId, scoreDto.customerId) &&
            Objects.equals(this.customerUsername, scoreDto.customerUsername) &&
            Objects.equals(this.customerIslogged, scoreDto.customerIslogged) &&
            Objects.equals(this.customerIsanonymous, scoreDto.customerIsanonymous) &&
            Objects.equals(this.quizId, scoreDto.quizId) &&
            Objects.equals(this.quizTitle, scoreDto.quizTitle) &&
            Objects.equals(this.quizDescription, scoreDto.quizDescription) &&
            Objects.equals(this.quizCreatedat, scoreDto.quizCreatedat) &&
            Objects.equals(this.quizMaxerrors, scoreDto.quizMaxerrors) &&
            Objects.equals(this.quizIsopen, scoreDto.quizIsopen) &&
            Objects.equals(this.completedaAt, scoreDto.completedaAt) &&
            Objects.equals(this.score, scoreDto.score);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, customerUsername, customerIslogged, customerIsanonymous, quizId, quizTitle, quizDescription, quizCreatedat, quizMaxerrors, quizIsopen, completedaAt, score);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScoreDto {\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    customerUsername: ").append(toIndentedString(customerUsername)).append("\n");
    sb.append("    customerIslogged: ").append(toIndentedString(customerIslogged)).append("\n");
    sb.append("    customerIsanonymous: ").append(toIndentedString(customerIsanonymous)).append("\n");
    sb.append("    quizId: ").append(toIndentedString(quizId)).append("\n");
    sb.append("    quizTitle: ").append(toIndentedString(quizTitle)).append("\n");
    sb.append("    quizDescription: ").append(toIndentedString(quizDescription)).append("\n");
    sb.append("    quizCreatedat: ").append(toIndentedString(quizCreatedat)).append("\n");
    sb.append("    quizMaxerrors: ").append(toIndentedString(quizMaxerrors)).append("\n");
    sb.append("    quizIsopen: ").append(toIndentedString(quizIsopen)).append("\n");
    sb.append("    completedaAt: ").append(toIndentedString(completedaAt)).append("\n");
    sb.append("    score: ").append(toIndentedString(score)).append("\n");
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
