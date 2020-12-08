package hr.fer.rznu.restexample.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserNotes {

    private List<NoteDTO> notes;

    public UserNotes(List<NoteDTO> notes) {
        this.notes = notes;
    }
}
