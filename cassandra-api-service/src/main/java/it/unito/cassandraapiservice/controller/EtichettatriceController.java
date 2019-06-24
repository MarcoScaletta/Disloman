package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.impl.etichettatrice.RecordsEtichettatrice;
import it.unito.cassandraapiservice.model.repository.RecordsEtichettatriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EtichettatriceController {

    @Autowired
    RecordsEtichettatriceRepository repository;

    @GetMapping("/records_etichettatrice")
    public List<RecordsEtichettatrice> getRecords()
    {
        Iterable<RecordsEtichettatrice> result = repository.findAll();
        List<RecordsEtichettatrice> recordsList = new ArrayList<>();
        result.forEach(recordsList::add);
        return recordsList;
    }

    @PostMapping("/records_etichettatrice")
    public List<RecordsEtichettatrice> addRecords(
            @RequestBody List<RecordsEtichettatrice> records){
        for (RecordsEtichettatrice r : records)
            repository.save(r);
        return records;
    }


    @DeleteMapping(value = "/records_etichettatrice/{id}",
            produces = "application/json; charset=utf-8")
    public String deleteRecord(@PathVariable String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
}
