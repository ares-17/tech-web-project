package com.unina.techweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.Objects;

/**
 * CustomerDto
 */
@AllArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-05T18:03:26.214085516+02:00[Europe/Rome]")
public class CustomerDto {

  @JsonProperty("id")
  private String id;

  @JsonProperty("username")
  private String username;

  @JsonProperty("isLogged")
  private Boolean isLogged;

  @JsonProperty("isAnonymous")
  private Boolean isAnonymous;

  @JsonProperty("password")
  private String password;

  public CustomerDto id(String id) {
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

  public CustomerDto username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  */
  @NotNull 
  @Schema(name = "username", required = true)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public CustomerDto isLogged(Boolean isLogged) {
    this.isLogged = isLogged;
    return this;
  }

  /**
   * Get isLogged
   * @return isLogged
  */
  
  @Schema(name = "isLogged", required = false)
  public Boolean getIsLogged() {
    return isLogged;
  }

  public void setIsLogged(Boolean isLogged) {
    this.isLogged = isLogged;
  }

  public CustomerDto isAnonymous(Boolean isAnonymous) {
    this.isAnonymous = isAnonymous;
    return this;
  }

  /**
   * Get isAnonymous
   * @return isAnonymous
  */
  
  @Schema(name = "isAnonymous", required = false)
  public Boolean getIsAnonymous() {
    return isAnonymous;
  }

  public void setIsAnonymous(Boolean isAnonymous) {
    this.isAnonymous = isAnonymous;
  }

  public CustomerDto password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  */
  @NotNull 
  @Schema(name = "password", required = true)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerDto customerDto = (CustomerDto) o;
    return Objects.equals(this.id, customerDto.id) &&
        Objects.equals(this.username, customerDto.username) &&
        Objects.equals(this.isLogged, customerDto.isLogged) &&
        Objects.equals(this.isAnonymous, customerDto.isAnonymous) &&
        Objects.equals(this.password, customerDto.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, isLogged, isAnonymous, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    isLogged: ").append(toIndentedString(isLogged)).append("\n");
    sb.append("    isAnonymous: ").append(toIndentedString(isAnonymous)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

