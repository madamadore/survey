package it.matteoavanzini.survey.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Question question;
    @OneToMany
    private List<Option> choosedOptions;
    private int total;

    public Answer() {}
    public Answer(Question question, List<Option> choosedOptions) {
        this.question = question;
        this.choosedOptions = choosedOptions;
    }
}