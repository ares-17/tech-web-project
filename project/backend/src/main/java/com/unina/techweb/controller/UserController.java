package com.unina.techweb.controller;

import com.unina.techweb.controller.api.UserApi;
import com.unina.techweb.dto.CustomerDto;
import com.unina.techweb.dto.QuizDto;
import com.unina.techweb.exceptions.NotFoundException;
import com.unina.techweb.exceptions.UserAlreadyExistsException;
import com.unina.techweb.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController implements UserApi {

    private final CustomerService customerService;

    @Autowired
    public UserController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomerById(String uidUser) {
        try{
            return ResponseEntity.ok(this.customerService.getUserById(uidUser));
        } catch (NotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<QuizDto>> getQuizListByUser(String uidUser) {
        try{
            return ResponseEntity.ok(this.customerService.getQuizByUser(uidUser));
        } catch (NotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<CustomerDto> createUser(CustomerDto customerDto) {
        try{
            var customerWithId = customerService.createCustomer(customerDto);
            return ResponseEntity.ok(customerWithId);
        } catch (UserAlreadyExistsException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
