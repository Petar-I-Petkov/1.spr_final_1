package com.petkov.spr_final_1.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = HomeController.class)
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("@Home MVC - When access '/home' with no credentials - isUnauthorized")
    public void whenAccessHomeWithNoCredentials_isUnauthorized() throws Exception {

        mockMvc
                .perform(get("/home"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("@Home MVC - When access '/home' with credentials - isOk")
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void whenAccessHomeWithCredentials_isOk() throws Exception {

        mockMvc
                .perform(get("/home"))
                .andExpect(status().isOk());
    }

}
