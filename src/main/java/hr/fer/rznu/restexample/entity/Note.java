package hr.fer.rznu.restexample.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    private User user;
}
