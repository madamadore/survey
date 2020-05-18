package it.matteoavanzini.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.repository.QuestionRepository;

@SpringBootApplication
public class SurveyApplication implements CommandLineRunner {

	@Autowired
	QuestionRepository questionRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Question simpleQuestion = getSimpleQuestion();
        Question multipleQuestion = getMultipleQuestion();

        questionRepository.save(simpleQuestion);
        questionRepository.save(multipleQuestion);
	}

	private Question getSimpleQuestion() {
        Question q = new Question();
        q.setTitle("Domanda");
        q.setId(1L);
        q.setDescription("di che colore era il cavallo bianco di Napoleone?");
        q.addOption(new Option(5L, "Bianco", 4));
        q.addOption(new Option(6L, "Nero", 0));
        return q;
    }

    private Question getMultipleQuestion() {
        Question q = new Question();
        q.setTitle("Domanda");
        q.setId(2L);
        q.setDescription("Quali sono i colori sociali del Bologna?");
        q.setMultiple(true);
        q.addOption(new Option(1L, "Rosso", 2));
        q.addOption(new Option(2L, "Nero", 0));
        q.addOption(new Option(3L, "Giallo", 0));
        q.addOption(new Option(4L, "Blu", 2));
        return q;
    }

	

}
