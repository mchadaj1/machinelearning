package com.example.services;


import com.example.entities.MethodConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RepositoryRestResource(path = "/methodconfiguration")
public interface MethodConfigurationRepository extends JpaRepository<MethodConfiguration, Long> {

    public MethodConfiguration findById(Long id);


}
