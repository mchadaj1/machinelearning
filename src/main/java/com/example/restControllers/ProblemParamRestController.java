package com.example.restControllers;

import com.example.entities.ProblemParam;
import com.example.services.MethodRepository;
import com.example.services.ProblemParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RestController
@RequestMapping("/services/problem_param")
public class ProblemParamRestController {


    ProblemParamRepository problem_paramRepository;

    @Autowired
    public void setProblem_paramRepository(ProblemParamRepository problem_paramRepository) {
        this.problem_paramRepository = problem_paramRepository;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<ProblemParam>> index(@PathVariable Long id){
        List<ProblemParam> problems =problem_paramRepository.findByProblemsid(id);
        if(problems.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(problems,HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<ProblemParam> delete(@PathVariable Long id)
    {
        ProblemParam problem_param = problem_paramRepository.findById(id);
        if(problem_param==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        problem_paramRepository.delete(problem_param);
        return new ResponseEntity<>(problem_param,HttpStatus.OK);
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<ProblemParam> create(@RequestBody(required = false) ProblemParam problem_param)
    {
        if(problem_param==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        problem_paramRepository.save(problem_param);
        return new ResponseEntity<>(problem_param,HttpStatus.OK);

    }

}
