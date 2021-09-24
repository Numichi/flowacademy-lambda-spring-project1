package com.example.demo.controllers;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserRequest;
import com.example.demo.services.AccountService;
import com.example.demo.services.LoggerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/login")
public class LoginController {

    private final AccountService accountService;
    private final LoggerService loggerService;

    LoginController(AccountService accountService, LoggerService loggerService) {
        this.accountService = accountService;
        this.loggerService = loggerService;
    }

    @PostMapping
    public boolean login(@RequestBody @Valid UserRequest user) {
        loggerService.auditInfo("User [" + user.getUsername() + "] is logging.");

        var model = accountService.findByUsername(user.getUsername());
        var passwordCorrect = accountService.passwordCheck(user.getPasswd(), model.getPasswd());

        if (passwordCorrect) {
            loggerService.auditInfo("User [" + user.getUsername() + "] login is success.");
        } else {
            loggerService.auditInfo("User [" + user.getUsername() + "] login is failed.");
        }

        return passwordCorrect;
    }

    @GetMapping
    public List<UserModel> test1() {
        return accountService.getAllUser();
    }
}
