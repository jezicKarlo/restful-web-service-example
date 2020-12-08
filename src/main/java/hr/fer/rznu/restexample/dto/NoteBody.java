package hr.fer.rznu.restexample.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NoteBody {

    @NotBlank
    private String content;
    @NotBlank
    private String name;
}
