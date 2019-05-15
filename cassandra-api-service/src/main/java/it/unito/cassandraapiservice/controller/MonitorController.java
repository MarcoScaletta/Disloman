package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.impl.smartshareobjects.Monitor;
import it.unito.cassandraapiservice.model.impl.tappatrice.RecordsTappatrice;
import it.unito.cassandraapiservice.model.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MonitorController {
    @Autowired
    MonitorRepository repository;

    @GetMapping("/monitor")
    public List<Monitor> getMonitors()
    {
        Iterable<Monitor> result = repository.findAll();
        List<Monitor> employeesList = new ArrayList<>();
        result.forEach(employeesList::add);
        return employeesList;
    }

    @PostMapping("/monitor")
    public Monitor addMonitors(@RequestBody Monitor monitor)
    {
        repository.save(monitor);
        return monitor;
    }


    @DeleteMapping(value = "/monitor/{id}", produces = "application/json; charset=utf-8")
    public String deleteMonitors(@PathVariable String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
}
