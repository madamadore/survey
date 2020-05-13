package it.matteoavanzini.survey.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private Long questionId;
    private List<Option> choose;
}