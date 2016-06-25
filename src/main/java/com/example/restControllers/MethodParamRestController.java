package com.example.restControllers;

import com.example.entities.MethodParam;
import com.example.services.MethodParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontroler dostarczający dane parametrów metod przez protokół HTTP.
 * Created by Mateusz on 2016-03-03.
 */
@RestController
@RequestMapping("/services/method_param")
public class MethodParamRestController {


    private final MethodParamRepository method_paramRepository;

    /**
     * Konstruktor klasy restowej parametrów metod.
     * @param method_paramRepository Repozytorium parametrów metod.
     */
    @Autowired
    public MethodParamRestController(MethodParamRepository method_paramRepository) {
        this.method_paramRepository = method_paramRepository;
    }

    /**
     * Funkcja dostarcza parametr metody przez protokół HTTP.
     * @param id Id parametru.
     * @return Parametr metody oraz status HTTP.
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<MethodParam>> index(@PathVariable Long id){
        List<MethodParam> methods =method_paramRepository.findByMethodsid(id);
        if(methods.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(methods,HttpStatus.OK);
    }

    /**
     * Metoda pozwala na usunięcie parametru metody przez protokół HTTP.
     * @param id Id parametru.
     * @return Usunięty parametr oraz status HTTP.
     */
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<MethodParam> delete(@PathVariable Long id)
    {
        MethodParam method_param = method_paramRepository.findById(id);
        if(method_param==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        method_paramRepository.delete(method_param);
        return new ResponseEntity<>(method_param,HttpStatus.OK);
    }

    /**
     * Funkcja pozwala na dodanie parametru do metody przez protokół HTTP.
     * @param method_param Parametr metody.
     * @return Wstawiony parametr metody oraz status HTTP.
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<MethodParam> create(@RequestBody(required = false) MethodParam method_param)
    {
        if(method_param==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        method_paramRepository.save(method_param);
        return new ResponseEntity<>(method_param,HttpStatus.OK);

    }


}
