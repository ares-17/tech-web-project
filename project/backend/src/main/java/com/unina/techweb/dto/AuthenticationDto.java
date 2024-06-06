package com.unina.techweb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * AuthenticationDto
 */
@AllArgsConstructor
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-06-05T18:28:13.530058051+02:00[Europe/Rome]")
public class AuthenticationDto {

  @JsonProperty("expiresIn")
  private BigDecimal expiresIn;

  @JsonProperty("token")
  private String token;

  @JsonProperty("idCustomer")
  private String idCustomer;

  public AuthenticationDto expiresIn(BigDecimal expiresIn) {
    this.expiresIn = expiresIn;
    return this;
  }

  /**
   * Get expiresIn
   * @return expiresIn
  */
  @Valid 
  @Schema(name = "expiresIn", required = false)
  public BigDecimal getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(BigDecimal expiresIn) {
    this.expiresIn = expiresIn;
  }

  public AuthenticationDto token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  */
  
  @Schema(name = "token", required = false)
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public AuthenticationDto idCustomer(String idCustomer) {
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
    AuthenticationDto authenticationDto = (AuthenticationDto) o;
    return Objects.equals(this.expiresIn, authenticationDto.expiresIn) &&
        Objects.equals(this.token, authenticationDto.token) &&
        Objects.equals(this.idCustomer, authenticationDto.idCustomer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expiresIn, token, idCustomer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthenticationDto {\n");
    sb.append("    expiresIn: ").append(toIndentedString(expiresIn)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

