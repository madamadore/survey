package it.matteoavanzini.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.repository.SurveyRepository;

@SpringBootApplication
public class SurveyApplication implements CommandLineRunner {

	@Autowired
	SurveyRepository surveyRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Question simpleQuestion = getSimpleQuestion();
        Question multipleQuestion = getMultipleQuestion();

        Survey survey = new Survey("Example");
        survey.addQuestion(simpleQuestion);
        survey.addQuestion(multipleQuestion);

        surveyRepository.save(survey);
	}

	private Question getSimpleQuestion() {
        Question q = new Question();
        q.setTitle("Domanda");
        q.setDescription("di che colore era il cavallo bianco di Napoleone?");
        q.addOption(new Option("Bianco", 4));
        q.addOption(new Option("Nero", 0));
        return q;
    }

    private Question getMultipleQuestion() {
        Question q = new Question();
        q.setTitle("Domanda");
        q.setDescription("Quali sono i colori sociali del Bologna?");
        q.setMultiple(true);
        q.addOption(new Option("Rosso", 2));
        q.addOption(new Option("Nero", 0));
        q.addOption(new Option("Giallo", 0));
        q.addOption(new Option("Blu", 2));
        return q;
    }

	

}
