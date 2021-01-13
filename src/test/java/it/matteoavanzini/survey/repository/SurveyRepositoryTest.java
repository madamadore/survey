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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import it.matteoavanzini.survey.model.Survey;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SurveyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private SurveyRepository surveyRepository;

    @Before
    public void setUp() {
        Survey survey = new Survey();
        survey.setTitle("Test Survey");
        survey.setDescription("description");

        entityManager.persist(survey);
        entityManager.flush();
    }

    @Test
    public void findSurveyWithTitle() {
        Optional<Survey> found = surveyRepository.findByTitle("Test Survey");
        assertTrue(found.isPresent(), "Nessun Survey");
        assertEquals("description", found.get().getDescription(), "La description dell'oggetto non è coerente");
    }

}