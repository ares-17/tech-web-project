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
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", authenticatedUser.getUsername());
        String jwtToken = jwtService.generateToken(extraClaims, authenticatedUser);

        return ResponseEntity.ok(new AuthenticationDto(
                BigDecimal.valueOf(jwtService.getExpirationTime()),
                jwtToken,
                authenticatedUser.getId().toString()));
    }

    @Override
    public ResponseEntity<AuthenticationDto> register(CustomerDto customerDto) {
        authenticationService.signup(customerDto);
        return this.login(customerDto);

    }
}
