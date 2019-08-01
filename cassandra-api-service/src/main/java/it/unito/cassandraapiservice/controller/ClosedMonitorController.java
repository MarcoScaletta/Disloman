package it.unito.cassandraapiservice.controller;

import com.datastax.driver.core.exceptions.NoHostAvailableException;
import it.unito.cassandraapiservice.model.impl.smartshareobjects.ClosedMonitor;
import it.unito.cassandraapiservice.model.repository.ClosedMonitorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.CassandraConnectionFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ClosedMonitorController {

    @Autowired
    ClosedMonitorRepository closedMonitorRepository;


    @GetMapping("/closed-monitor")
    public ResponseEntity<List<ClosedMonitor>> getClosedMonitors(){
        try{
            Iterable<ClosedMonitor> result = closedMonitorRepository.findAll();
            List<ClosedMonitor> employeesList = new ArrayList<>();
            result.forEach(employeesList::add);
            return ResponseEntity.ok(employeesList);
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/closed-monitor")
    public ResponseEntity<ClosedMonitor> addClosedMonitor(@RequestBody ClosedMonitor closedMonitor){
        try{
            closedMonitorRepository.save(closedMonitor);
            return ResponseEntity.ok(closedMonitor);
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    @DeleteMapping(value = "/closed-monitor/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deleteClosedMonitor(@PathVariable String id) {
        try{
            boolean result = closedMonitorRepository.existsById(id);
            closedMonitorRepository.deleteById(id);
            return ResponseEntity.ok("{ \"success\" : "+ (result ? "true" : "false") +" }");

        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

}
