package com.unina.techweb.controller;

import com.unina.techweb.api.UserApi;
import com.unina.techweb.model.Quiz;
import com.unina.techweb.model.User;
import com.unina.techweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController implements UserApi {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> getUserById(String uidUser) {
        return this.userService.getUserById(uidUser);
    }

    @Override
    public ResponseEntity<List<Quiz>> getQuizByUser(String uidUser) {
        return this.userService.getQuizByUser(uidUser);
    }
}
