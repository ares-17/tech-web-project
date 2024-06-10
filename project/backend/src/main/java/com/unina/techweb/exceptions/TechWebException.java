package com.unina.techweb.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class TechWebException extends RuntimeException {
    private final HttpStatus code;
    private final String message;
    private final String title;
}
