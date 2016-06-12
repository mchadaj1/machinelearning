package com.example.services;


import com.example.entities.MethodParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RepositoryRestResource
public interface MethodParamRepository extends JpaRepository<MethodParam, Long> {

   public MethodParam findById(Long id);
    public List<MethodParam> findByMethodsid(Long problemsid);

}
