package it.matteoavanzini.survey.model;

public class Option {
    public static long ID = 0;

    private String text;
    private int value;
    private long id;

    public Option() { this(null, 0); }
    public Option(String text) { this(text, 0); }
    public Option(String text, int value) { 
        this.id = ++Option.ID;
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