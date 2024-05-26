package com.unina.techweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Setter
@Getter
@AllArgsConstructor
public class AuthenticationDto {

    String token;
    long expiresIn;
}
