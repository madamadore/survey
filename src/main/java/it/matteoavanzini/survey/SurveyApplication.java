package it.matteoavanzini.survey;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import it.matteoavanzini.survey.config.YamlPropertySourceFactory;
import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;

@SpringBootApplication
@PropertySource(value = "classpath:survey.yml", factory = YamlPropertySourceFactory.class)
public class SurveyApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {        
        
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
