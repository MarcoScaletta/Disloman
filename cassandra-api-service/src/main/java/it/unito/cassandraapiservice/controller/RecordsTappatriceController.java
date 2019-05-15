package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.impl.tappatrice.RecordsTappatrice;
import it.unito.cassandraapiservice.model.repository.RecordsTappatriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecordsTappatriceController {
    @Autowired
    RecordsTappatriceRepository repository;

    @GetMapping("/records_tappatrice")
    public List<RecordsTappatrice> getRecords()
    {
        Iterable<RecordsTappatrice> result = repository.findAll();
        List<RecordsTappatrice> recordsList = new ArrayList<>();
        result.forEach(recordsList::add);
        return recordsList;
    }

    @PostMapping("/records_tappatrice")
    public RecordsTappatrice addRecord(@RequestBody RecordsTappatrice recordsTappatrice)
    {
        repository.save(recordsTappatrice);
        return recordsTappatrice;
    }


    @DeleteMapping(value = "/records_tappatrice/{id}", produces = "application/json; charset=utf-8")
    public String deleteRecord(@PathVariable String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }
}
