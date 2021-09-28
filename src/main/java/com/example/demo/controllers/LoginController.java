package com.example.demo.controllers;

import com.example.demo.exceptions.WrongPasswordException;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRequest;
import com.example.demo.services.AccountService;
import com.example.demo.services.JwtService;
import com.example.demo.services.LoggerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/login")
public class LoginController {

    private final AccountService accountService;
    private final LoggerService loggerService;
    private final JwtService jwtService;

    LoginController(AccountService accountService, LoggerService loggerService, JwtService jwtService) {
        this.accountService = accountService;
        this.loggerService = loggerService;
        this.jwtService = jwtService;
    }

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public String login(@RequestBody @Valid UserRequest user) {
        loggerService.auditInfo("User [" + user.getUsername() + "] is logging.");

        var model = accountService.findByUsername(user.getUsername());
        var passwordCorrect = accountService.passwordCheck(user.getPasswd(), model.getPasswd());

        if (passwordCorrect) {
            loggerService.auditInfo("User [" + user.getUsername() + "] login is success.");
        } else {
            loggerService.auditInfo("User [" + user.getUsername() + "] login is failed.");
            throw new WrongPasswordException();
        }

        return jwtService.createJwt(user.getUsername());
    }

    @GetMapping("check1")
    @PreAuthorize("hasRole('ADMIN2')")
    public String check() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping
    public List<UserModel> test1() {
        return accountService.getAllUser();
    }
}
