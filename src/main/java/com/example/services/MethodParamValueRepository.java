package com.example.services;


import com.example.entities.MethodParamValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RepositoryRestResource(path = "/method_param_values")
public interface MethodParamValueRepository extends JpaRepository<MethodParamValue, Long> {

    public MethodParamValue findByMethodParamIdAndMethodConfigurationId(Long methodParamId, Long methodConfigurationId);
    public List<MethodParamValue> findByMethodConfigurationId(Long methodConfigurationId);

}
