package it.matteoavanzini.survey.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSurveyTest {
    
    @Autowired
    private JavaSurvey survey;
    
    @Autowired
    Environment env;

    @Value("${spring.datasource.username}")
    String dbUsername;
    
    @Test
    public void testSurvey() {
        assertEquals("Spring Boot", survey.getTitle());
        assertEquals(20, survey.getQuestions().size());
    }

}