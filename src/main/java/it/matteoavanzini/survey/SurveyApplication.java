package it.matteoavanzini.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.matteoavanzini.survey.config.SpringSurvey;
import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.User;
import it.matteoavanzini.survey.repository.SurveyRepository;
import it.matteoavanzini.survey.repository.UserRepository;

@SpringBootApplication
@EnableConfigurationProperties(SpringSurvey.class)
public class SurveyApplication implements CommandLineRunner {

	@Autowired
    SurveyRepository surveyRepository;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
	
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

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.addRole("ROLE_ADMIN");

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.addRole("ROLE_USER");

        userRepository.save(admin);
        userRepository.save(user);
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
