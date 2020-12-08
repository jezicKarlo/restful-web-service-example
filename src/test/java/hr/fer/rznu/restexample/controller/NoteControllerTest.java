package hr.fer.rznu.restexample.controller;

import hr.fer.rznu.restexample.utils.GsonGenerator;
import hr.fer.rznu.restexample.utils.NoteGenerator;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {

    @Autowired
    private NoteController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void smokeTest() {
        assertNotNull(controller);
    }

    @Test
    public void getNotesTest() throws Exception {
        mockMvc.perform(post("/api/users/1/notes")
                .queryParam("token", UserGenerator.getKJEZIC_TOKEN())
                .contentType(MediaType.APPLICATION_JSON)
                .content(GsonGenerator.getGson().toJson(NoteGenerator.noteBody_kjezic())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.name").exists())
                .andExpect(jsonPath("$.data.content").exists());

        mockMvc.perform(get("/api/users/1/notes")
                .queryParam("token", UserGenerator.getKJEZIC_TOKEN()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.*", Matchers.hasSize(1)));

    }

}