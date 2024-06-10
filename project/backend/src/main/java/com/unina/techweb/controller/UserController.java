package com.unina.techweb.controller;

import com.unina.techweb.controller.api.UserApi;
import com.unina.techweb.dto.CustomerDto;
import com.unina.techweb.dto.QuizDto;
import com.unina.techweb.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(this.customerService.getUserById(uidUser));
    }

    @Override
    public ResponseEntity<List<QuizDto>> getQuizListByUser(String uidUser) {
        return ResponseEntity.ok(this.customerService.getQuizByUser(uidUser));
    }

    @Override
    public ResponseEntity<CustomerDto> createUser(CustomerDto customerDto) {
        var customerWithId = customerService.createCustomer(customerDto);
        return ResponseEntity.ok(customerWithId);
    }

}
