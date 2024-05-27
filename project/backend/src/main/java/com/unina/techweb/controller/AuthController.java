package com.unina.techweb.controller;

import com.unina.techweb.controller.api.AuthApi;
import com.unina.techweb.dto.AuthenticationDto;
import com.unina.techweb.dto.CustomerDto;
import com.unina.techweb.entities.Customer;
import com.unina.techweb.service.AuthenticationService;
import com.unina.techweb.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController implements AuthApi {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Override
    public ResponseEntity<AuthenticationDto> login(CustomerDto customerDto) {
        Customer authenticatedUser = authenticationService.authenticate(customerDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        AuthenticationDto dto = new AuthenticationDto(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<AuthenticationDto> register(CustomerDto customerDto) {
        authenticationService.signup(customerDto);
        return this.login(customerDto);
    }
}
