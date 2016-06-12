package com.example.services;


import com.example.entities.AlgorithmExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RepositoryRestResource
public interface AlgorithmExecutionRepository extends JpaRepository<AlgorithmExecution, Long> {

    public List<AlgorithmExecution> findByPendingTrueAndCompletedFalse();
    public AlgorithmExecution findById(Long id);

}
