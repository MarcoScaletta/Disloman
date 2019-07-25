package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.impl.smartshareobjects.OpenMonitor;
import it.unito.cassandraapiservice.model.repository.OpenMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OpenMonitorController {

    @Autowired
    OpenMonitorRepository openMonitorRepository;


    @GetMapping("/open-monitor")
    public OpenMonitor getOpenMonitors(){
        Iterable<OpenMonitor> result = openMonitorRepository.findAll();

        if(result.iterator().hasNext())
            return result.iterator().next();
        return null;
    }

    @PostMapping("/open-monitor")
    public OpenMonitor addOpenMonitors(@RequestBody OpenMonitor openMonitor){
        openMonitorRepository.save(openMonitor);
        return openMonitor;
    }

    @DeleteMapping(value = "/open-monitor/{id}/", produces = "application/json; charset=utf-8")
    public String deleteOpenMonitor(@PathVariable String id) {
        boolean result = openMonitorRepository.existsById(id);
        openMonitorRepository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }


}
