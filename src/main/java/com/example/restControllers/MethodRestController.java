package com.example.restControllers;

/**
 * Created by mateusz on 05.03.16.
 */

import com.example.entities.Method;
import com.example.services.MethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/method")
public class MethodRestController {


    MethodRepository methodRepository;

    @Autowired
    public void setMethodRepository(MethodRepository methodRepository) {
        this.methodRepository = methodRepository;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<Method>> index(){
        List<Method>methods =methodRepository.findAll();
        if(methods.isEmpty())
            return new ResponseEntity<List<Method>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Method>>(methods,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Method> get( @PathVariable Long id)
    {
        Method method = methodRepository.findById(id);
        if(method == null)
            return new ResponseEntity<Method>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Method>(method,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Method> update(@PathVariable Long id,@RequestBody Method method)
    {
        Method currentMethod = methodRepository.findById(id);
        if(currentMethod==null)
            return new ResponseEntity<Method>(HttpStatus.NOT_FOUND);


        currentMethod.setName(method.getName());
        currentMethod.setDescription(method.getDescription());
        currentMethod.setImports(method.getImports());
        currentMethod.setConstructorcode(method.getConstructorcode());
        currentMethod.setCode(method.getCode());
        currentMethod.setGlobals(method.getGlobals());
        currentMethod.setFinishgame(method.getFinishgame());
        methodRepository.save(currentMethod);
        return new ResponseEntity<Method>(method,HttpStatus.OK);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Method> delete(@PathVariable Long id)
    {
        Method method = methodRepository.findById(id);
        if(method==null)
            return new ResponseEntity<Method>(HttpStatus.NOT_FOUND);
        methodRepository.delete(id);
        return new ResponseEntity<Method>(method,HttpStatus.OK);
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<Method> create(@RequestBody(required = false) Method method)
    {
        if(method==null)
            return new ResponseEntity<Method>(HttpStatus.NOT_FOUND);
        methodRepository.save(method);
        return new ResponseEntity<Method>(method,HttpStatus.OK);

    }
}
