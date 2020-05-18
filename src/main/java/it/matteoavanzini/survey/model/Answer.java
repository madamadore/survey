package it.matteoavanzini.survey.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {

    private long id;

    private Question question;
    private List<Option> choosedId;
    private int total;

    public Answer() {}
    public Answer(Question question, List<Option> choose) {
        this.question = question;
        this.choosedId = choose;
    }

    public int calculateTotal() {
        int total = 0;
        for (Option o: question.getOptions()) {
            if (choosedId.contains(o)) {
                total += o.getValue();
            }
        }
        return total;
    }
}