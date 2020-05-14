package it.matteoavanzini.survey.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Survey {
    private static long ID = 0;

    private long id;
    private String title;
    private String description;
    private List<Question> questions;

    public Survey() {
        this.id = ++ID;
    }

    public Survey(String title) {
        this();
        this.title = title;
    }

    public Survey(String title, List<Question> questions) {
        this(title);
        this.questions = questions;
    }
}