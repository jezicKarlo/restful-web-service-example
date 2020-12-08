package hr.fer.rznu.restexample.controller;

import com.google.gson.reflect.TypeToken;
import hr.fer.rznu.restexample.dto.NoteDTO;
import hr.fer.rznu.restexample.utils.GsonGenerator;
import hr.fer.rznu.restexample.utils.NoteGenerator;
import hr.fer.rznu.restexample.utils.ResponseParser;
import hr.fer.rznu.restexample.utils.UserGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void postGetTest() throws Exception {
        ResultActions resultActions = post(UserGenerator.getKJEZIC_TOKEN(), UserGenerator.getKjezicId());
        resultActions.andExpect(jsonPath("$.data").exists())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.name").exists())
                .andExpect(jsonPath("$.data.content").exists());

        mockMvc.perform(get("/api/users/1/notes")
                .queryParam("token", UserGenerator.getKJEZIC_TOKEN()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.*").isArray());
    }

    @Test
    public void getSpecificNoteTest() throws Exception {
        ResultActions post = post(UserGenerator.getKJEZIC_TOKEN(), UserGenerator.getKjezicId());
        String content = post.andReturn().getResponse().getContentAsString();

        ResponseParser<NoteDTO> response = GsonGenerator.getGson().fromJson(content,
                new TypeToken<ResponseParser<NoteDTO>>() {
                }.getType());

        NoteDTO saved = response.getData();

        mockMvc.perform(get("/api/users/" + UserGenerator.getKjezicId() + "/notes/" + saved.getId())
                .queryParam("token", UserGenerator.getKJEZIC_TOKEN()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.name").exists())
                .andExpect(jsonPath("$.data.name").value(saved.getName()))
                .andExpect(jsonPath("$.data.content").exists())
                .andExpect(jsonPath("$.data.content").value(saved.getContent()));
    }

    @Test
    public void postTest_unauthorized() throws Exception {
        ResultActions resultActions = post(UserGenerator.getKJEZIC_TOKEN(), UserGenerator.getKendaId());
        resultActions.andExpect(status().isUnauthorized());
    }

    private ResultActions post(String token, Integer id) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/api/users/" + id + "/notes")
                .queryParam("token", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(GsonGenerator.getGson().toJson(NoteGenerator.noteBody_kjezic())));
    }

}