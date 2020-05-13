package it.matteoavanzini.survey.model;

public class Option {
    private String text;
    private int value;

    public Option() {}
    public Option(String text) { this.text = text; }
    public Option(String text, int value) { 
        this.text = text; 
        this.value = value;
    }

    public void setText(String text) { this.text = text; } 
    public String getText() { return text; } 

    public void setValue(int value) { this.value = value; } 
    public int getValue() { return value; } 

    @Override
    public String toString() {
        return "{ text: " + text + ", value: " + value + " }";
    }
}