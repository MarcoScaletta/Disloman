package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.impl.incartonatrice.RecordsIncartonatrice;
import it.unito.cassandraapiservice.model.repository.RecordsIncartonatriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IncartonatriceController {

    @Autowired
    RecordsIncartonatriceRepository repository;

    @GetMapping("/records_incartonatrice")
    public List<RecordsIncartonatrice> getRecords(){
        Iterable<RecordsIncartonatrice> result = repository.findAll();
        List<RecordsIncartonatrice> recordsList = new ArrayList<>();
        result.forEach(recordsList::add);
        return recordsList;
    }

    @PostMapping("/records_incartonatrice")
    public List<RecordsIncartonatrice> addRecords(
            @RequestBody List<RecordsIncartonatrice> records){
        for (RecordsIncartonatrice r : records)
            repository.save(r);
        return records;
    }


    @DeleteMapping(value = "/records_incartonatrice/{id}",
            produces = "application/json; charset=utf-8")
    public String deleteRecord(@PathVariable String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
}
