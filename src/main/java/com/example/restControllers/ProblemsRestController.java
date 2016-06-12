package com.example.restControllers;

import com.example.entities.Problem;
import com.example.services.ProblemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RestController
@RequestMapping("/services/problem")
public class ProblemsRestController {


    ProblemsRepository problemsRepository;
    @Autowired
    public void setProblemsRepository(ProblemsRepository problemsRepository) {
        this.problemsRepository = problemsRepository;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<Problem>> index(){
        List<Problem> problems =problemsRepository.findAll();
        if(problems.isEmpty())
            return new ResponseEntity<List<Problem>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Problem>>(problems,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Problem> get( @PathVariable Long id)
    {
        Problem problem = problemsRepository.findById(id);
            if(problem == null)
                return new ResponseEntity<Problem>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<Problem>(problem,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Problem> update(@PathVariable Long id,@RequestBody Problem problem)
    {
        Problem currentProblem = problemsRepository.findById(id);
        if(currentProblem==null)
            return new ResponseEntity<Problem>(HttpStatus.NOT_FOUND);
        //

        currentProblem.setName(problem.getName());
        currentProblem.setDescription(problem.getDescription());

        problemsRepository.save(currentProblem);
        return new ResponseEntity<Problem>(problem,HttpStatus.OK);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Problem> delete(@PathVariable Long id)
    {
        Problem problem = problemsRepository.findById(id);
        if(problem==null)
            return new ResponseEntity<Problem>(HttpStatus.NOT_FOUND);
        problemsRepository.delete(id);
        return new ResponseEntity<Problem>(problem,HttpStatus.OK);
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<Problem> create(@RequestBody(required = false) Problem problem)
    {
        if(problem==null)
            return new ResponseEntity<Problem>(HttpStatus.NOT_FOUND);
        problemsRepository.save(problem);
        return new ResponseEntity<Problem>(problem,HttpStatus.OK);

    }


}
