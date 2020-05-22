package it.matteoavanzini.survey.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import it.matteoavanzini.survey.model.Survey;

@Configuration
@PropertySource("classpath:survey.yml")
public class SpringSurvey {

    @Bean
    @ConfigurationProperties
    public Survey getJavaSurvey() {
        return new Survey();
    }

}