package com.example.demo.services;

import com.example.demo.exceptions.WrongPasswordException;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRequest;
import com.example.demo.store.UserStoreRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountService {

    private final UserStoreRepository userStoreRepository;
    private final PasswordEncoder encoder;

    public AccountService(UserStoreRepository userStoreRepository, PasswordEncoder encoder) {
        this.userStoreRepository = userStoreRepository;
        this.encoder = encoder;
    }

    public void saveUser(UserRequest user) {
        UserModel model = new UserModel();

        model.setUsername(user.getUsername());
        model.setPasswd(encoder.encode(user.getPasswd()));

        userStoreRepository.addUser(model);
    }

    public UserModel findByUsername(String username) {
        return userStoreRepository.findAll()
                .stream()
                .filter((var item) -> item.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User ["+ username +"] not exist!"));
    }

    public boolean passwordCheck(String passwd, String passwd1) {
        return encoder.matches(passwd, passwd1);
    }

    public List<UserModel> getAllUser() {
        return userStoreRepository.findAll();
    }
}
