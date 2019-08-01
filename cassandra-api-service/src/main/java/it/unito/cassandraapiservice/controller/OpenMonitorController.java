package it.unito.cassandraapiservice.controller;

import com.datastax.driver.core.exceptions.NoHostAvailableException;
import it.unito.cassandraapiservice.model.impl.smartshareobjects.OpenMonitor;
import it.unito.cassandraapiservice.model.repository.OpenMonitorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.CassandraConnectionFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class OpenMonitorController {

    @Autowired
    OpenMonitorRepository openMonitorRepository;


    @GetMapping("/open-monitor")
    public ResponseEntity<OpenMonitor> getOpenMonitors(){
        try{
            Iterable<OpenMonitor> result = openMonitorRepository.findAll();
            if(result.iterator().hasNext())
                return ResponseEntity.ok(result.iterator().next());
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
        return null;
    }

    @PostMapping("/open-monitor")
    public ResponseEntity<OpenMonitor> addOpenMonitors(@RequestBody OpenMonitor openMonitor){
        try{
            openMonitorRepository.save(openMonitor);
            return ResponseEntity.ok(openMonitor);
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping(value = "/open-monitor/{id}/", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deleteOpenMonitor(@PathVariable String id) {
        try{
            boolean result = openMonitorRepository.existsById(id);
            openMonitorRepository.deleteById(id);
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
