package com.example.restControllers;

/**
 * Created by mateusz on 05.03.16.
 */

import com.example.entities.MethodConfiguration;
import com.example.services.MethodConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/method_configuration")
public class MethodConfigurationRestController {

    MethodConfigurationRepository method_configurationRepository;
    @Autowired
    public void setMethod_configurationRepository(MethodConfigurationRepository method_configurationRepository) {
        this.method_configurationRepository = method_configurationRepository;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<MethodConfiguration>> index(){
        List<MethodConfiguration>method_configurations = method_configurationRepository.findAll();
        if(method_configurations.isEmpty())
            return new ResponseEntity<List<MethodConfiguration>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<MethodConfiguration>>(method_configurations,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<MethodConfiguration> get(@PathVariable Long id)
    {
        MethodConfiguration method_configuration = method_configurationRepository.findById(id);
        if(method_configuration == null)
            return new ResponseEntity<MethodConfiguration>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<MethodConfiguration>(method_configuration,HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MethodConfiguration> update(@PathVariable Long id, @RequestBody MethodConfiguration method_configuration)
    {
        MethodConfiguration currentMethod_configuration = method_configurationRepository.findById(id);
        if(currentMethod_configuration==null)
            return new ResponseEntity<MethodConfiguration>(HttpStatus.NOT_FOUND);
        //

        currentMethod_configuration.setName(method_configuration.getName());

        method_configurationRepository.save(currentMethod_configuration);
        return new ResponseEntity<MethodConfiguration>(method_configuration,HttpStatus.OK);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<MethodConfiguration> delete(@PathVariable Long id)
    {
        MethodConfiguration method_configuration = method_configurationRepository.findById(id);
        if(method_configuration==null)
            return new ResponseEntity<MethodConfiguration>(HttpStatus.NOT_FOUND);
        method_configurationRepository.delete(id);
        return new ResponseEntity<MethodConfiguration>(method_configuration,HttpStatus.OK);
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<MethodConfiguration> create(@RequestBody(required = false) MethodConfiguration method_configuration)
    {
        if(method_configuration==null)
            return new ResponseEntity<MethodConfiguration>(HttpStatus.NOT_FOUND);
        method_configurationRepository.save(method_configuration);
        return new ResponseEntity<MethodConfiguration>(method_configuration,HttpStatus.OK);

    }
}
