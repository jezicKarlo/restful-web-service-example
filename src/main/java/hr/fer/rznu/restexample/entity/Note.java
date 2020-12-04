package hr.fer.rznu.restexample.entity;

import lombok.Data;

import java.util.List;

@Data
public class Note {

    private int id;

    private String name;
    private List<Note> songs;
}
