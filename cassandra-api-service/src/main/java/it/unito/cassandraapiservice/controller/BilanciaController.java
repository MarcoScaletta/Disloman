package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.impl.bilancia.RecordsBilancia;
import it.unito.cassandraapiservice.model.repository.RecordsBilanciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BilanciaController {

    @Autowired
    RecordsBilanciaRepository repository;

    @GetMapping("/records_bilancia")
    public List<RecordsBilancia> getRecords()
    {
        Iterable<RecordsBilancia> result = repository.findAll();
        List<RecordsBilancia> recordsList = new ArrayList<>();
        result.forEach(recordsList::add);
        return recordsList;
    }

    @PostMapping("/records_bilancia")
    public List<RecordsBilancia> addRecords(
            @RequestBody List<RecordsBilancia> records){
        for (RecordsBilancia r : records)
            repository.save(r);
        return records;
    }


    @DeleteMapping(value = "/records_bilancia/{id}",
            produces = "application/json; charset=utf-8")
    public String deleteRecord(@PathVariable String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
}
