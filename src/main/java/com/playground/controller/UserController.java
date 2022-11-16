package com.playground.controller;

import com.playground.repository.UserRepo;
import com.playground.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(@RequestBody User user){
        userRepo.save(user);
        return "User saved successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers(){
        return userRepo.findAll();
    }
}
