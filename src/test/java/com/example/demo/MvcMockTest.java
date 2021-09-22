package com.example.demo;

import com.example.demo.model.UserModel;
import com.example.demo.model.UserRequest;
import com.example.demo.store.UserStoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan
@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Őt azért, hogy ne static legyen a @BeforeAll
public class MvcMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserStoreRepository repository;

    private Long timer;

    @BeforeAll
    private void setAll() {

    }

    @BeforeEach
    public void setUp() {
        var userModel = new UserModel();
        userModel.setUsername("alma");
        userModel.setPasswd("korte");

//        given(repository.findAll()).willReturn(List.of(userModel));

        timer = Instant.now().toEpochMilli();
    }

    @AfterEach
    public void speed() {
        System.out.println("#####");
        System.out.println(Instant.now().toEpochMilli() - timer);
    }

    @Test
    @DisplayName("Get all users")
    public void getAll() throws Exception {
        mockMvc.perform(get("/api/login")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // Ez Disabled
    @Test
    @DisplayName("Get all users by mock")
    @Disabled
    public void getAll2() throws Exception {
        mockMvc.perform(get("/api/login")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].username").value("alma"))
                .andExpect(jsonPath("$[0].passwd").value("korte"));
    }

    // Ez Failed, mert @MockBean van. @SpyBean-el jó lenne.
    @Test
    @DisplayName("Set a user, and get all")
    public void getAll3() throws Exception {
        var user = UserRequest.builder().username("aaaa").passwd("ssss").build();
        var json = new ObjectMapper().writeValueAsString(user);

        mockMvc.perform(post("/api/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        mockMvc.perform(get("/api/login")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].username").value("aaaa"));
    }
}
