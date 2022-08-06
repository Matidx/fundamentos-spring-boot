package com.fundamentos.spring.fundamentos.controller;

import com.fundamentos.spring.fundamentos.caseuse.CreateUser;
import com.fundamentos.spring.fundamentos.caseuse.GetUser;
import com.fundamentos.spring.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/users")
public class UserRestController {
    //create, get, delete, update
    private GetUser getUser;
    private CreateUser createUser;

    public UserRestController(GetUser getUser, CreateUser createUser) {
        this.getUser = getUser;
        this.createUser = createUser;
    }

    @GetMapping("/")
    List<User> get () {
        return getUser.getAll();
    }

    @PostMapping ("/")
    ResponseEntity<User> newUser (@RequestBody User newUser) {
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }
}
