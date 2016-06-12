package com.example.restControllers;

import com.example.entities.MethodParamValue;
import com.example.services.MethodParamValueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Mateusz on 2016-03-03.
 */
@RestController
@RequestMapping("/resources/methods/configurations/")
public class MethodParamValueRestController {


    MethodParamValueRepository method_param_valueRepository;

    @Autowired
    public void setMethod_param_valueRepository(MethodParamValueRepository method_param_valueRepository) {
        this.method_param_valueRepository = method_param_valueRepository;
    }

    @RequestMapping(value = "/{configuration_id}/params/{param_id}/value",method = RequestMethod.GET)
    public ResponseEntity<MethodParamValue> get(@PathVariable Long configuration_id, @PathVariable Long param_id)
    {
        MethodParamValue value = method_param_valueRepository.findByMethodParamIdAndMethodConfigurationId(param_id,configuration_id);
            return new ResponseEntity<>(value,HttpStatus.OK);
    }
    @RequestMapping(value = "/{configuration_id}/params/{param_id}/value",method = RequestMethod.PUT)
    public ResponseEntity<MethodParamValue> update(@PathVariable Long configuration_id, @PathVariable Long param_id, @RequestBody String value){
        MethodParamValue param_value;
        MethodParamValue param_value1 = method_param_valueRepository.findByMethodParamIdAndMethodConfigurationId(param_id,configuration_id);
        if(param_value1==null)
            param_value = method_param_valueRepository.save(new MethodParamValue(param_id,configuration_id,value));
        else {
            param_value1.setValue(value);
            param_value = method_param_valueRepository.save(param_value1);
        }
        return new ResponseEntity<>(param_value,HttpStatus.OK);
    }



}
