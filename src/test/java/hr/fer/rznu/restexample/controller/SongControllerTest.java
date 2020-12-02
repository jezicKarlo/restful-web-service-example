package hr.fer.rznu.restexample.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SongControllerTest {

    @Autowired
    private SongController controller;

    @Test
    public void smokeTest() {
        assertNotNull(controller);
    }
}