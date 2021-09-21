package com.example.demo.controllers;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserRequest;
import com.example.demo.services.AccountService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/login")
public class LoginController {

    private static final Logger log = LogManager.getLogger("AUDIT");

    private final AccountService accountService;

    LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public boolean login(@RequestBody @Valid UserRequest user) {
        log.info("User [" + user.getUsername() + "] is logging.");

        var model = accountService.findByUsername(user.getUsername());
        var passwordCorrect = accountService.passwordCheck(user.getPasswd(), model.getPasswd());

        if (passwordCorrect) {
            log.info("User [" + user.getUsername() + "] login is success.");
        } else {
            log.info("User [" + user.getUsername() + "] login is failed.");
        }

        return passwordCorrect;
    }

    @GetMapping
    public List<UserModel> test1() {
        return accountService.getAllUser();
    }
}
