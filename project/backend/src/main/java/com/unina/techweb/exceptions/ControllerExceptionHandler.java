package com.unina.techweb.exceptions;

import com.unina.techweb.dto.Problem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> handleGenericException(
            Exception ex, WebRequest request){

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
        Problem problem = new Problem();
        problem.setInstance(uri.toString());

        if(ex instanceof TechWebException twException){
            problem.setDetail(ex.getMessage());
            problem.setTitle(twException.getTitle());
            problem.setStatus(twException.getCode().value());
            log.error(problem.toString());
            return handleExceptionInternal(ex, problem, new HttpHeaders(), twException.getCode(), request);
        }
        String cause = (ex.getMessage() != null) ? ex.getMessage() : "An error occurred";
        problem.setDetail(cause);
        problem.setTitle("Error occurred");
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(problem.toString());
        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = TechWebException.class)
    protected ResponseEntity<Object> handleNotFoundExceptions(TechWebException ex, WebRequest request) {
        return this.handleGenericException(ex, request);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadySignUpExceptions(UserAlreadyExistsException ex, WebRequest request) {
        return this.handleGenericException(ex, request);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return this.handleGenericException(ex, request);
    }
}
