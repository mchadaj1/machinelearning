package com.example.services;


import com.example.entities.ProblemConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RepositoryRestResource(path = "/problemconfiguration")
public interface ProblemConfigurationRepository extends JpaRepository<ProblemConfiguration, Long> {

    public ProblemConfiguration findById(Long id);

}
