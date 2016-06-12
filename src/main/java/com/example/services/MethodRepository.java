package com.example.services;


import com.example.entities.Method;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RepositoryRestResource(path = "/method")
public interface MethodRepository extends JpaRepository<Method, Long> {

    public Method findById(Long id);

}
