package com.example.demo.store;

import com.example.demo.database.User;
import com.example.demo.database.UserRepository;
import com.example.demo.model.UserModel;
import com.example.demo.services.LoggerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserStoreRepository {

    private final UserRepository userRepository;
    private final LoggerService loggerService;

    UserStoreRepository(UserRepository userRepository, LoggerService loggerService) {
        this.userRepository = userRepository;
        this.loggerService = loggerService;
    }

    public void addUser(UserModel user) {
        var dbUser = User.builder()
                .username(user.getUsername())
                .passwd(user.getPasswd())
                .build();

        try {
            userRepository.save(dbUser);
        } catch (ConstraintViolationException e) {
            loggerService.systemError(e.getMessage());
            throw e;
        }
    }

    public List<UserModel> findAll() {
        List<UserModel> result = new ArrayList<>();

        userRepository.findAll().forEach((var user) -> {
            var model = new UserModel();
            model.setUsername(user.getUsername());
            model.setPasswd(user.getPasswd());
            result.add(model);
        });

        return result;
    }

    public UserModel findById(int userId) {
        var user = userRepository.findById(userId).orElseThrow();

        var model = new UserModel();

        model.setUsername(user.getUsername());
        model.setPasswd(user.getPasswd());
        return model;
    }

    public void remove(int user) {
        userRepository.deleteById(user);
    }
}
