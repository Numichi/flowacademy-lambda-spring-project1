package com.example.demo;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserRequest;
import com.example.demo.services.AccountService;
import com.example.demo.store.UserStoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class SpringSpecificTest {

    @Autowired
    AccountService accountService;

    @MockBean
    UserStoreRepository userStoreRepository;

    @Test
    public void test1() {
        var user = UserRequest.builder()
                .passwd("passwd")
                .username("valamiUser")
                .build();

        var userModel = new UserModel();
        userModel.setUsername(user.getUsername());
        userModel.setPasswd(user.getPasswd());

        //given
        given(userStoreRepository.findAll()).willReturn(List.of(userModel));

        // when
        accountService.saveUser(user);

        //then
        assertEquals(1, userStoreRepository.findAll().size());
    }
}
