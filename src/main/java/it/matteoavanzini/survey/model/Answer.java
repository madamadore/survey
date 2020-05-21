package it.matteoavanzini.survey.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Question question;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="answer_option", 
        joinColumns=@JoinColumn(name="answer", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="option", referencedColumnName="id"))
    private List<Option> choosedOptions;
    private int total;

    public Answer() {}
    public Answer(Question question, List<Option> choosedOptions) {
        this.question = question;
        this.choosedOptions = choosedOptions;
    }
}