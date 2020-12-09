package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.dto.RegisterForm;
import hr.fer.rznu.restexample.utils.GsonGenerator;
import hr.fer.rznu.restexample.utils.RegisterFormGenerator;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private UserController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(get("/api/users")
                .queryParam("username", "kjezic")
                .queryParam("password", "1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data..token").exists())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.token").value("20aa0ab9-b698-4786-96f5-9f81302ef576"))
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    public void loginTest_wrongPassword() throws Exception {
        mockMvc.perform(get("/api/users")
                .queryParam("username", "kjezic")
                .queryParam("password", "111"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void registerTest() throws Exception {
        RegisterForm registerForm = RegisterFormGenerator.createRealRegisterForm();
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GsonGenerator.getGson().toJson(registerForm)))
                .andExpect(status().isCreated());
    }

    @Test
    public void registerTest_badRequest() throws Exception {
        RegisterForm registerForm = RegisterFormGenerator.createBadRegisterForm();
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GsonGenerator.getGson().toJson(registerForm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void profileTest() throws Exception {
        mockMvc.perform(get("/api/users/1")
                .queryParam("token", UserGenerator.getKJEZIC_TOKEN()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.username").exists())
                .andExpect(jsonPath("$.data.username").value("kjezic"))
                .andExpect(jsonPath("$.data.firstName").exists())
                .andExpect(jsonPath("$.data.firstName").value("Karlo"))
                .andExpect(jsonPath("$.data.lastName").exists())
                .andExpect(jsonPath("$.data.lastName").value("Jezic"));
    }

    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/api/users/1")
                .queryParam("token", UserGenerator.getKJEZIC_TOKEN()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/1")
                .queryParam("token", UserGenerator.getKJEZIC_TOKEN()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void editUser() throws Exception {
        mockMvc.perform(get("/api/users/1")
                .queryParam("token", UserGenerator.getKJEZIC_TOKEN()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.username").exists())
                .andExpect(jsonPath("$.data.username").value("kjezic"));

        mockMvc.perform(put("/api/users/1")
                .queryParam("token", "20aa0ab9-b698-4786-96f5-9f81302ef576")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GsonGenerator.getGson().toJson(UserGenerator.createKjezic_toEdit())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.username").exists())
                .andExpect(jsonPath("$.data.username").value("karlo.jezic"));
    }
}