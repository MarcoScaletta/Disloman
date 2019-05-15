package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.impl.smartshareobjects.Fase;
import it.unito.cassandraapiservice.model.repository.FaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FaseController {
    @Autowired
    FaseRepository repository;

    @GetMapping("/fase")
    public List<Fase> getMonitors()
    {
        Iterable<Fase> result = repository.findAll();
        List<Fase> fasi = new ArrayList<>();
        result.forEach(fasi::add);
        return fasi;
    }

    @PostMapping("/fase")
    public Fase addMonitors(@RequestBody Fase fase)
    {
        repository.save(fase);
        return fase;
    }


    @DeleteMapping(value = "/fase/{id}", produces = "application/json; charset=utf-8")
    public String deleteMonitors(@PathVariable String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
}
