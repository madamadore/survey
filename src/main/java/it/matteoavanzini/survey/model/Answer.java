package it.matteoavanzini.survey.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Answer {
    private Question question;
    private List<Long> choose;
    private int total;

    public Answer() {}
    public Answer(Question question, List<Long> choose) {
        this.question = question;
        this.choose = choose;
    }

    public int calculateTotal() {
        int total = 0;
        for (Option o: question.getOptions()) {
            if (choose.contains(o.getId())) {
                total += o.getValue();
            }
        }
        return total;
    }
}