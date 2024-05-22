package com.unina.techweb.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.unina.techweb.entities.Customer}
 */
@Value
@Setter
@Getter
@AllArgsConstructor
public class CustomerDto implements Serializable {

    UUID id;

    @NotNull
    @Size(max = 255)
    String username;

    @NotNull
    Boolean isLogged;

    @NotNull
    Boolean isAnonymous;
}