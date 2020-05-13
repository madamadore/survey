package it.matteoavanzini.survey.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {
    public static long ID = 0;
    
    private long id;
    private String title;
    private String description;
    private boolean multiple;
    private List<Option> options;

    public Question() {
        this.id = ++Question.ID;
        this.options = new ArrayList<>();
    }
    
    public void addOption(Option o) {
        options.add(o);
    }
}