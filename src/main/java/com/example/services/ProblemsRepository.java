package com.example.services;


import com.example.entities.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RepositoryRestResource
public interface ProblemsRepository extends JpaRepository<Problem, Long> {

    public Problem findById(Long id);

}
