package it.matteoavanzini.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Option {
    public static long ID = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length=150)
    private String text;
    private int value;

    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

    public Option() { this(null, 0); }
    public Option(String text) { this(text, 0); }
    public Option(String text, int value) { 
        this(++Option.ID, text, value);
    }
    public Option(long id, String text, int value) { 
        this.id = id;
        this.text = text; 
        this.value = value;
    }

    public void setText(String text) { this.text = text; } 
    public String getText() { return text; } 

    public void setValue(int value) { this.value = value; } 
    public int getValue() { return value; } 

    public void setId(long id) { this.id = id; } 
    public long getId() { return id; } 

    @Override
    public String toString() {
        return "{ text: " + text + ", value: " + value + " }";
    }
}