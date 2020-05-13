package it.matteoavanzini.survey.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Survey {
    private long id;
    private String title;
    private String description;
    private List<Question> questions;
}