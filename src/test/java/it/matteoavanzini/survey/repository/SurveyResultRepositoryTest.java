package it.matteoavanzini.survey.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SurveyResultRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private SurveyResultRepository surveyResultRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private SurveyResult surveyResult;
    private User user;
    private Survey survey;

    @Before
    public void setUp() {
        survey = new Survey();
        survey.setTitle("Sample");
        entityManager.persist(survey);

        user = new User();
        user.setUsername("adminTest");
        entityManager.persist(user);

        surveyResult = new SurveyResult();
        surveyResult.setUser(user);
        surveyResult.setSurvey(survey);
        entityManager.persist(surveyResult);

        entityManager.flush();
    }

    @Test
    public void testFindSurveyResultByUserAndSurvey() {
        Optional<SurveyResult> found = surveyResultRepository.findByUserAndSurvey(user, survey);
        assertTrue(found.isPresent());
        assertEquals("adminTest", found.get().getUser().getUsername());
        assertEquals("Sample", found.get().getSurvey().getTitle());
    }
}