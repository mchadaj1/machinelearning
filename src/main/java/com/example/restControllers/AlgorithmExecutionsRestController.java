package com.example.restControllers;

/**
 * Created by mateusz on 05.03.16.
 */

import com.example.entities.AlgorithmExecution;
import com.example.services.AlgorithmExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/algorithm_execution")
public class AlgorithmExecutionsRestController {

    AlgorithmExecutionRepository algorithmExecutionRepository;
    @Autowired
    public void setAlgorithmExecutionRepository(AlgorithmExecutionRepository algorithmExecutionRepository) {
        this.algorithmExecutionRepository = algorithmExecutionRepository;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<AlgorithmExecution>> index(){
        List<AlgorithmExecution>algorithmExecutions =algorithmExecutionRepository.findAll();
        if(algorithmExecutions.isEmpty())
            return new ResponseEntity<List<AlgorithmExecution>>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<AlgorithmExecution>>(algorithmExecutions,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<AlgorithmExecution> get(@PathVariable Long id)
    {
        AlgorithmExecution algorithmExecution = algorithmExecutionRepository.findById(id);
        if(algorithmExecution == null)
            return new ResponseEntity<AlgorithmExecution>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<AlgorithmExecution>(algorithmExecution,HttpStatus.OK);
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<AlgorithmExecution> create(@RequestBody(required = false) AlgorithmExecution algorithmExecution)
    {
        if(algorithmExecution==null)
            return new ResponseEntity<AlgorithmExecution>(HttpStatus.NOT_FOUND);
        algorithmExecutionRepository.save(algorithmExecution);
        return new ResponseEntity<AlgorithmExecution>(algorithmExecution,HttpStatus.OK);

    }
}
