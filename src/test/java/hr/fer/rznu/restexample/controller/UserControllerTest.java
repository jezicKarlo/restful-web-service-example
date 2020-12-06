package hr.fer.rznu.restexample.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private UserController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(get("/api/users")
                .param("username", "kjezic")
                .param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
}