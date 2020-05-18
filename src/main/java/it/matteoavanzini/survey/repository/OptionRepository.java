package it.matteoavanzini.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.matteoavanzini.survey.model.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {
    
}