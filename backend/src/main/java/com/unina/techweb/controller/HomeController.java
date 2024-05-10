package com.unina.techweb.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    public String getDefault(){
        return "Hello!";
    }

    @GetMapping("{happy}")
    public String displayHomePageWithPathParameter(@PathVariable(required = false) Boolean happy){
        return (happy) ? "Hello!": "GoodBye!";
    }

    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody @NotNull Object user){
        return ResponseEntity.ok(user);
    }

    @PostMapping("request-param")
    public String examplePathParameter(@Valid @RequestParam(required = false) String category){
        return category;
    }
}
