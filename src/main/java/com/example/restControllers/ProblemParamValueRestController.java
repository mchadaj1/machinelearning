package com.example.restControllers;

import com.example.entities.ProblemParamValue;
import com.example.services.ProblemParamValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RestController
@RequestMapping("/services/problem_param_value")
public class ProblemParamValueRestController {


    ProblemParamValueRepository problem_param_valueRepository;
    @Autowired
    public void setProblem_param_valueRepository(ProblemParamValueRepository problem_param_valueRepository) {
        this.problem_param_valueRepository = problem_param_valueRepository;
    }

    @RequestMapping(value = "/{configuration_id}/{param_id}",method = RequestMethod.GET)
    public ResponseEntity<ProblemParamValue> get(@PathVariable Long configuration_id, @PathVariable Long param_id)
    {
        ProblemParamValue value = problem_param_valueRepository.findByProblemParamIdAndProblemConfigurationId(param_id,configuration_id);
            return new ResponseEntity<>(value,HttpStatus.OK);
    }
    @RequestMapping(value = "/{configuration_id}/{param_id}",method = RequestMethod.PUT)
    public ResponseEntity<ProblemParamValue> update(@PathVariable Long configuration_id, @PathVariable Long param_id, @RequestBody String value){
        ProblemParamValue param_value;
        ProblemParamValue param_value1 = problem_param_valueRepository.findByProblemParamIdAndProblemConfigurationId(param_id,configuration_id);
        if(param_value1==null)
            param_value = problem_param_valueRepository.save(new ProblemParamValue(param_id,configuration_id,value));
        else {
            param_value1.setValue(value);
            param_value = problem_param_valueRepository.save(param_value1);
        }
        return new ResponseEntity<>(param_value,HttpStatus.OK);
    }
}
