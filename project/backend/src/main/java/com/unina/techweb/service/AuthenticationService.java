package com.unina.techweb.service;

import com.unina.techweb.dto.CustomerDto;
import com.unina.techweb.entities.Customer;
import com.unina.techweb.exceptions.TWNotFoundException;
import com.unina.techweb.exceptions.UserAlreadyExistsException;
import com.unina.techweb.repository.CustomerRepository;
import com.unina.techweb.utils.Mapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            CustomerRepository customerRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer signup(CustomerDto input) {
        Optional<Customer> existUsername  = customerRepository.findByUsername(input.getUsername());
        if(existUsername.isPresent()){
            throw new UserAlreadyExistsException(input.getUsername());
        }

        var dtoWithEncodePwd = new CustomerDto(
                input.getId(),
                input.getUsername(),
                passwordEncoder.encode(input.getPassword())
        );

        Customer customer = Mapper.mapCustomerDtoToCustomer(dtoWithEncodePwd);
        return customerRepository.save(customer);
    }

    public Customer authenticate(CustomerDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return customerRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new TWNotFoundException(input.getUsername()));
    }
}

