package com.unina.techweb.service;

import com.unina.techweb.dto.CustomerDto;
import com.unina.techweb.dto.QuizDto;
import com.unina.techweb.entities.Customer;
import com.unina.techweb.repository.CustomerRepository;
import com.unina.techweb.repository.QuizRepository;
import com.unina.techweb.utils.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        return Mapper.mapCustomerToCustomerDto(this.customerRepository.getById(UUID.fromString(uidUser)));
    }

    public List<QuizDto> getQuizByUser(String uidUser) {
        Customer customer = this.customerRepository.getById(UUID.fromString(uidUser));

        return this.quizRepository.findByIdUser(customer).stream()
                .map(q -> Mapper.mapQuizToQuizDTO(q, List.of())).toList();
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        var customer = new Customer();
        customer.setIsanonymous(customerDto.getIsAnonymous());
        customer.setIslogged(customerDto.getIsLogged());
        customer.setUsername(customerDto.getUsername());

        var id = customerRepository.save(customer).getId();
        return new CustomerDto(
                id,
                customerDto.getUsername(),
                customerDto.getIsLogged(),
                customerDto.getIsAnonymous()
                );
    }
}
