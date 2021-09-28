package com.example.demo.controllers;

import com.example.demo.model.UserRequest;
import com.example.demo.services.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {

    private final AccountService accountService;

    RegistrationController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public boolean registration(@RequestBody @Valid UserRequest user) {
        accountService.saveUser(user);
        return true;
    }
}
