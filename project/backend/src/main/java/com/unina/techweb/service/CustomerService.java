package com.unina.techweb.service;

import com.unina.techweb.dto.CustomerDto;
import com.unina.techweb.dto.QuizDto;
import com.unina.techweb.entities.Customer;
import com.unina.techweb.exceptions.TWNotFoundException;
import com.unina.techweb.exceptions.UserAlreadyExistsException;
import com.unina.techweb.repository.CustomerRepository;
import com.unina.techweb.repository.QuizRepository;
import com.unina.techweb.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final QuizRepository quizRepository;

    @Autowired
    CustomerService(
            CustomerRepository repository,
            QuizRepository quizRepository){
        this.customerRepository = repository;
        this.quizRepository = quizRepository;
    }

    public CustomerDto getUserById(String uidUser) {
        Customer customer = this.customerRepository.findById(UUID.fromString(uidUser))
                .orElseThrow(() -> new TWNotFoundException(uidUser));
        return Mapper.mapCustomerToCustomerDto(customer);
    }

    public List<QuizDto> getQuizByUser(String uidUser) {
        Customer customer = this.customerRepository.findById(UUID.fromString(uidUser))
                .orElseThrow(() -> new TWNotFoundException(uidUser));

        return this.quizRepository.findByIdUser(customer).stream()
                .map(q -> Mapper.mapQuizToQuizDTO(q, List.of())).toList();
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Optional<Customer> existAnotherSameCustomer = this.customerRepository.findByUsername(customerDto.getUsername());
        if(existAnotherSameCustomer.isPresent()){
            throw new UserAlreadyExistsException(customerDto.getUsername());
        }

        var customer = new Customer();
        customer.setUsername(customerDto.getUsername());

        var id = customerRepository.save(customer).getId();
        return new CustomerDto(
                id.toString(),
                customerDto.getUsername(),
                "" // password is not needed
                );
    }

    @Transactional
    public Customer createNonAuthenticableUser(final String username) {
        Optional<Customer> customer = this.customerRepository.findByUsername(username);
        return customer.orElseGet(() -> {
            Customer user = new Customer();
            user.setPassword("");
            user.setUsername(username);

            return customerRepository.save(user);
        });

    }
}
