package com.bnp.kata.onlinebookstore.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecurityFilterChain securityFilterChain;

    @Test
    public void validateGeneralEndpointsToBeAccessible() throws Exception {
        mockMvc.perform(post("/api/v1/user/register")
                        .contentType("application/json")
                        .content("{\"userName\":\"user\", \"password\":\"password\", \"email\":\"email@example.com\", \"firstName\":\"First\", \"lastName\":\"Last\"}"))
                .andExpect(status().isCreated());
    }

}
