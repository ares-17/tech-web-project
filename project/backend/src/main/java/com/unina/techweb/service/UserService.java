package com.unina.techweb.service;

import com.unina.techweb.model.Quiz;
import com.unina.techweb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    public ResponseEntity<User> getUserById(String uidUser) {
        throw new UnsupportedOperationException();
    }

    public ResponseEntity<List<Quiz>> getQuizByUser(String uidUser) {
        throw new UnsupportedOperationException();
    }
}
