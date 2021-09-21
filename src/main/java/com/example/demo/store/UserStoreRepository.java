package com.example.demo.store;

import com.example.demo.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserStoreRepository {
    private final Map<Integer, UserModel> store = new HashMap<>();
    private int increment = 1;

    public void addUser(UserModel user) {
        store.put(increment++, user);
    }

    public List<UserModel> findAll() {
        List<UserModel> result = new ArrayList<>();
        store.forEach((var key, var model) -> {
            result.add(model);
        });
        return result;
    }

    public UserModel findById(int user) {
        return store.get(user);
    }

    public void remove(int user) {
        store.remove(user);
    }
}
