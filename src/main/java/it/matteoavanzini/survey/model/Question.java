package it.matteoavanzini.survey.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String description;
    private boolean multiple;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Option> options;

    public Question() {
        this.options = new ArrayList<>();
    }
    
    public void addOption(Option o) {
        options.add(o);
    }
}