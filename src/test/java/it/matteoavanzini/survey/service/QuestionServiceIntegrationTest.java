package it.matteoavanzini.survey.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;
import it.matteoavanzini.survey.repository.SurveyRepository;
import it.matteoavanzini.survey.repository.SurveyResultRepository;
import it.matteoavanzini.survey.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceIntegrationTest {
    
    @Autowired
    QuestionService questionService;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SurveyResultRepository surveyResultRepository;

    Question expectedSecondQuestion;
    User user;
    Survey survey;

    private Question getFirstSampleQuestion() {
        Question q = new Question();
        q.setTitle("Matematica");
        q.setDescription("Quanto fa 2^2?");
        q.addOption(new Option("22", 0));
        q.addOption(new Option("4", 4));
        q.addOption(new Option("8", 0));
        q.addOption(new Option("256", 0));
        return q;
    }

    private Question getSecondSampleQuestion() {
        Question q = new Question();
        q.setTitle("Cultura");
        q.setDescription("Chi ha scoperto la Pennicilina?");
        q.addOption(new Option("Einstein", 0));
        q.addOption(new Option("Flaming", 4));
        q.addOption(new Option("Washington", 0));
        q.addOption(new Option("Churcill", 0));
        return q;
    }

    @Before
    public void setUp() {
        survey = new Survey();
        survey.setTitle("Sample test");
        survey.addQuestion(getFirstSampleQuestion());
        survey.addQuestion(getSecondSampleQuestion());
        surveyRepository.save(survey);

        user = new User();
        user.setUsername("adminTest");
        userRepository.save(user);

    }
    
    @Test
    public void testGetResult() {

        SurveyResult surveyResult = new SurveyResult();
        surveyResult.setUser(user);
        surveyResult.setSurvey(survey);
        surveyResultRepository.save(surveyResult);

        SurveyResult result = questionService.getResult(user, survey);
        assertNotNull(result);
    }

    @Test
    public void testCreateSurveyResult() {
        questionService.createSurveyResult(user, survey);

        SurveyResult result = questionService.getResult(user, survey);
        assertNotNull(result);
        assertNotNull(result.getStartDate());
        assertNotNull(result.getSurvey());
        assertNotNull(result.getUser());
    }
    
    // voglio verificare che mi restituisca sempre lo stesso SurveyResult anche in caso di duplicazione
    public void testCreateDuplicatedSurveyResult() {
        
    }
    
    @Test
    public void testCloseSurveyResult() throws InterruptedException {
        questionService.createSurveyResult(user, survey);

        Thread.sleep(1000);
        
        questionService.closeSurveyResult(user, survey);
        SurveyResult result = questionService.getResult(user, survey);
        assertNotNull(result.getEndDate());

        Date startDate = result.getStartDate();
        Date endDate = result.getEndDate();
        assertTrue(startDate.before(endDate)); 
    }
    
}