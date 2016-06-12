package com.example.services;


import com.example.entities.ProblemParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RepositoryRestResource
public interface ProblemParamRepository extends JpaRepository<ProblemParam, Long> {

   public ProblemParam findById(Long id);
    public List<ProblemParam> findByProblemsid(Long problemsid);

}
