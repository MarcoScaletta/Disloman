package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.impl.smartshareobjects.ClosedMonitor;
import it.unito.cassandraapiservice.model.repository.ClosedMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClosedMonitorController {

    @Autowired
    ClosedMonitorRepository closedMonitorRepository;


    @GetMapping("/closed-monitor")
    public List<ClosedMonitor> getClosedMonitors(){
        Iterable<ClosedMonitor> result = closedMonitorRepository.findAll();
        List<ClosedMonitor> employeesList = new ArrayList<>();
        result.forEach(employeesList::add);
        return employeesList;
    }

    @PostMapping("/closed-monitor")
    public ClosedMonitor addClosedMonitor(@RequestBody ClosedMonitor closedMonitor){
        closedMonitorRepository.save(closedMonitor);
        return closedMonitor;
    }


    @DeleteMapping(value = "/closed-monitor/{id}", produces = "application/json; charset=utf-8")
    public String deleteClosedMonitor(@PathVariable String id) {
        boolean result = closedMonitorRepository.existsById(id);
        closedMonitorRepository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }

}
