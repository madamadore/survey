package it.matteoavanzini.survey.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.repository.OptionRepository;
import it.matteoavanzini.survey.repository.QuestionRepository;
import it.matteoavanzini.survey.repository.SurveyRepository;
import it.matteoavanzini.survey.repository.SurveyResultRepository;

@RunWith(SpringRunner.class)
public class QuestionServiceTest {
    
    @TestConfiguration
    static class QuestionServiceTestContextConfiguration {
  
        @MockBean
        OptionRepository optionRepository;

        @MockBean
        SurveyResultRepository surveyResultRepository;

        @MockBean
        QuestionRepository questionRepository;

        @Bean
        public QuestionService questionService() {
            return new QuestionServiceImpl();
        }
    }

    @Autowired
    QuestionService questionService;

    @MockBean
    SurveyRepository surveyRepository;

    Question expectedSecondQuestion;

    private Question getFirstSampleQuestion() {
        Question q = new Question();
        q.setTitle("Matematica");
        q.setId(1L);
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
        q.setId(2L);
        q.setDescription("Chi ha scoperto la Pennicilina?");
        q.addOption(new Option("Einstein", 0));
        q.addOption(new Option("Flaming", 4));
        q.addOption(new Option("Washington", 0));
        q.addOption(new Option("Churcill", 0));
        return q;
    }

    @Before
    public void setUp() {
        expectedSecondQuestion = getSecondSampleQuestion();
        Survey survey = new Survey();
        survey.setTitle("Sample test");
        survey.addQuestion(getFirstSampleQuestion());
        survey.addQuestion(expectedSecondQuestion);

        Optional<Survey> optional = Optional.of(survey);
        Mockito
            .when(surveyRepository.findById(1L))
            .thenReturn(optional);
    }

    @Test
    public void testNext() {
        Question question = questionService.next(1L, 1L);
        assertEquals(expectedSecondQuestion, question);
    }

    public void testNextOnLastQuestion() {}
    public void testCreateSurveyResult() {}
    public void testCreateDuplicatedSurveyResult() {}
    public void testCloseSurveyResult() {}
    
}