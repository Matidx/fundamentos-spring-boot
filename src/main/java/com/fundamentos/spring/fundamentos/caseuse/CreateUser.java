package com.fundamentos.spring.fundamentos.caseuse;

import com.fundamentos.spring.fundamentos.entity.User;
import com.fundamentos.spring.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
