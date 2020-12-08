package hr.fer.rznu.restexample.entity;

import hr.fer.rznu.restexample.dto.NoteBody;
import hr.fer.rznu.restexample.dto.NoteDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String content;
    @ManyToOne
    private User user;

    public Note() {
    }

    public Note(NoteBody body) {
        this.name = body.getName();
        this.content = body.getContent();
    }
}
