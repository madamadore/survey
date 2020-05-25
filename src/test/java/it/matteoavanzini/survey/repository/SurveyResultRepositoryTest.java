package it.matteoavanzini.survey.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Before
    public void setUp() {
        user = new User();
        user.setUsername("adminTest");
        entityManager.persist(user);

        surveyResult = new SurveyResult();
        surveyResult.setUser(user);
        entityManager.persist(surveyResult);

        entityManager.flush();
    }

    @Test
    public void findSurveyResultWithUsernameTest() {
        Optional<SurveyResult> found = surveyResultRepository.findByUser(user);
        assertTrue(found.isPresent());
        assertEquals("adminTest", found.get().getUser().getUsername());
    }

}