package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.impl.smartshareobjects.ODL;
import it.unito.cassandraapiservice.model.repository.ODLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ODLController {
    @Autowired
    ODLRepository repository;

    @GetMapping("/odl")
    public List<ODL> getODLs()
    {
        Iterable<ODL> result = repository.findAll();
        List<ODL> odlsList = new ArrayList<>();
        result.forEach(odlsList::add);
        return odlsList;
    }

    @PostMapping("/odl")
    public ODL addMonitors(@RequestBody ODL odl)
    {

        repository.save(odl);
        return odl;
    }


    @DeleteMapping(value = "/odl/{id}", produces = "application/json; charset=utf-8")
    public String deleteODLs(@PathVariable String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
}
