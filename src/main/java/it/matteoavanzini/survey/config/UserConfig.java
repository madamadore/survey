package it.matteoavanzini.survey.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.matteoavanzini.survey.model.User;
import it.matteoavanzini.survey.repository.UserRepository;

@Component
public class UserConfig {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    List<User> users;
        
    @PostConstruct
    public void insertUsers() {

        users = new ArrayList<User>() {{
            add(new User("Caso", "Claudio", "c.caso@cineca.it", passwordEncoder.encode(".caso")));
            add(new User("De Gregorio", "Sabrina", "s.degregorio@cineca.it", passwordEncoder.encode(".degregorio")));
            add(new User("Guerra", "Fabrizio", "f.guerra@cineca.it", passwordEncoder.encode(".guerra")));
            add(new User("Monteferrante", "Francesco", "f.monteferrante@cineca.it", passwordEncoder.encode(".monteferrante")));
            add(new User("Mottola", "AnnaMaria", "a.mottola@cineca.it", passwordEncoder.encode(".mottola")));
            add(new User("Rauli", "Federico", "f.rauli@cineca.it", passwordEncoder.encode(".rauli")));
            add(new User("Righi", "Daniele", "d.righi@cineca.it", passwordEncoder.encode(".righi")));
            add(new User("Squarcia", "Roberta", "r.squarcia@cineca.it", passwordEncoder.encode(".squarcia")));
            add(new User("Sun", "Bin", "b.sun@cineca.it", passwordEncoder.encode(".sun")));
            add(new User("Vivarelli", "Samuel", "s.vivarelli@cineca.it", passwordEncoder.encode(".vivarelli")));
            add(new User("Avanzini", "Matteo", "matteo.avanzini@gmail.com", passwordEncoder.encode("ava10"), "ROLE_ADMIN"));
        }};

        for (User u : users) {
            userRepository.save(u);
        }
    }
}