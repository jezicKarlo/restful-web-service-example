package hr.fer.rznu.restexample.dto;

import hr.fer.rznu.restexample.entity.Note;
import lombok.Data;

@Data
public class NoteDTO {

    private int id;
    private String content;
    private String name;

    public NoteDTO(Note note) {
        this.id = note.getId();
        this.content = note.getContent();
        this.name = note.getName();
    }
}
