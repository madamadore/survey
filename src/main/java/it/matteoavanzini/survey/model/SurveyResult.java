package it.matteoavanzini.survey.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyResult {
    private Date startDate;
    private Date endDate;
    private List<Answer> answers;
    private int total;

    public SurveyResult() {
        startDate = new Date();
        answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void endSurvey() {
        endDate = new Date();
        for (Answer a: answers) {
            total += a.calculateTotal();
        }
    }
}