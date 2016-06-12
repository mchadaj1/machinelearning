package com.example.services;


import com.example.entities.ProblemParamValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RepositoryRestResource(path = "/problem_param_values")
public interface ProblemParamValueRepository extends JpaRepository<ProblemParamValue, Long> {

    public ProblemParamValue findByProblemParamIdAndProblemConfigurationId(Long problemParamId, Long problemConfigurationId);

    List<ProblemParamValue> findByProblemConfigurationId(Long id);

}
