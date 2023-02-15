package com.test.salon.controller;

import com.test.salon.exceptions.BadRequestException;
import com.test.salon.model.User;
import com.test.salon.service.UserService;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1/users")
public class RestController {

    UserService userService;

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        throw new BadRequestException("lol");
    }
}
