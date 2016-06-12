package com.example.restControllers;

/**
 * Created by mateusz on 05.03.16.
 */

import com.example.entities.ProblemConfiguration;
import com.example.services.ProblemConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/problem_configuration")
public class ProblemConfigurationRestController {

    ProblemConfigurationRepository problem_configurationRepository;

    @Autowired
    public void setProblem_configurationRepository(ProblemConfigurationRepository problem_configurationRepository) {
        this.problem_configurationRepository = problem_configurationRepository;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<ProblemConfiguration>> index(){
        List<ProblemConfiguration>problem_configurations = problem_configurationRepository.findAll();
        if(problem_configurations.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(problem_configurations,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<ProblemConfiguration> get(@PathVariable Long id)
    {
        ProblemConfiguration problem_configuration = problem_configurationRepository.findById(id);
        if(problem_configuration == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(problem_configuration,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProblemConfiguration> update(@PathVariable Long id, @RequestBody ProblemConfiguration problem_configuration)
    {
        ProblemConfiguration currentProblem_configuration = problem_configurationRepository.findById(id);
        if(currentProblem_configuration==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //

        currentProblem_configuration.setName(problem_configuration.getName());


        problem_configurationRepository.save(currentProblem_configuration);
        return new ResponseEntity<>(problem_configuration,HttpStatus.OK);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<ProblemConfiguration> delete(@PathVariable Long id)
    {
        ProblemConfiguration problem_configuration = problem_configurationRepository.findById(id);
        if(problem_configuration==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        problem_configurationRepository.delete(id);
        return new ResponseEntity<>(problem_configuration,HttpStatus.OK);
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<ProblemConfiguration> create(@RequestBody(required = false) ProblemConfiguration problem_configuration)
    {
        if(problem_configuration==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        problem_configurationRepository.save(problem_configuration);
        return new ResponseEntity<>(problem_configuration,HttpStatus.OK);

    }

}
