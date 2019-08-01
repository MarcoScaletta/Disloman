package it.unito.cassandraapiservice.controller;


import com.datastax.driver.core.exceptions.NoHostAvailableException;
import it.unito.cassandraapiservice.model.apiobjects.*;
import it.unito.cassandraapiservice.model.impl.realtime.RealTime;
import it.unito.cassandraapiservice.model.machinepersistentoperations.BilanciaOperations;
import it.unito.cassandraapiservice.model.machinepersistentoperations.EtichettatriceOperations;
import it.unito.cassandraapiservice.model.machinepersistentoperations.IncartonatriceOperations;
import it.unito.cassandraapiservice.model.machinepersistentoperations.TappatriceOperations;
import it.unito.cassandraapiservice.model.repository.RealTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.CassandraConnectionFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class GenericMachineController {

    @Autowired
    TappatriceOperations tappatriceOperations;

    @Autowired
    IncartonatriceOperations incartonatriceOperations;

    @Autowired
    EtichettatriceOperations etichettatriceOperations;

    @Autowired
    BilanciaOperations bilanciaOperations;

    @Autowired
    RealTimeRepository realTimeRepository;

    @GetMapping("/api/ssb/")
    public String test()
    {
        return "OK";
    }

    @GetMapping("/records/{macchina}/")
    public ResponseEntity<RecordsList> getRecords(@PathVariable String macchina){
        try{
            return ResponseEntity.ok(getMachineOperations(macchina).getRecords());
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/records/{macchina}/")
    public ResponseEntity<RecordsList> addRecords(@PathVariable String macchina,
                                                  @RequestBody RecordsList records){
        try{
            return ResponseEntity.ok(getMachineOperations(macchina).addRecords(records));
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    @DeleteMapping(value = "/records/{macchina}/{id}/",
            produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deleteRecord(@PathVariable String macchina, @PathVariable String id) {
        try{
            return ResponseEntity.ok(getMachineOperations(macchina).deleteRecord(id));
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    // EXTERNAL API

    @GetMapping("api/risultati/macchina/{macchina}/")
    public ResponseEntity<RisultatiList> getRisultati(@PathVariable String macchina){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return ResponseEntity.status(404).body(null);
        try{
            return ResponseEntity.ok(machineOperations.getRisultati());
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("api/risultati/macchina/{macchina}/commessa/{commessa}/")
    public ResponseEntity<RisultatiCommessaList> getRisultatiCommessa(
            @PathVariable String macchina,
            @PathVariable String commessa){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return ResponseEntity.status(404).body(null);
        try{
            return ResponseEntity.ok(machineOperations.getRisultatiCommessa(commessa));

        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("api/risultati_turno/macchina/{macchina}/commessa/{commessa}/")
    public ResponseEntity<RisultatiCommessaTurnoList> getRisultatiCommessaTurno(
            @PathVariable String macchina,
            @PathVariable String commessa){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return ResponseEntity.status(404).body(null);
        try{
            return ResponseEntity.ok(machineOperations.getRisultatiCommessaTurno(commessa));

        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("api/risultati_turno/macchina/{macchina}/odl/{odl}/")
    public ResponseEntity<RisultatiODLTurnoList> getRisultatiODLTurno(
            @PathVariable String macchina,
            @PathVariable String odl){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return ResponseEntity.status(404).body(null);
        try{
            return ResponseEntity.ok(machineOperations.getRisultatiODLTurno(odl));
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }

    }

    @GetMapping("api/real_time/macchina/{macchina}/")
    public ResponseEntity<RealTime> getRealTimeMacchina(@PathVariable String macchina){
        Optional<RealTime> result = realTimeRepository.findById(macchina);
        try{
            return result.map(ResponseEntity::ok).orElseGet(() ->
                    ResponseEntity.status(404).body(null));
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("api/anomalie/macchina/{macchina}/")
    public ResponseEntity<AnomalieList> getAnomalie(@PathVariable String macchina){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        try{
            if (machineOperations == null)
                return ResponseEntity.status(404).body(null);
            return ResponseEntity.ok(machineOperations.getAnomalie());
        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("api/tempo-ciclo/macchina/{macchina}/")
    public ResponseEntity<TempoCicloList> getTempoCiclo(@PathVariable String macchina){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        try{
            if (machineOperations == null)
                return ResponseEntity.status(404).body(null);
            return ResponseEntity.ok(machineOperations.getTempoCiclo());

        }catch (CassandraConnectionFailureException | NoHostAvailableException e){
            log.error(ExceptionError.noHostAvailable());
            return ResponseEntity.status(503).body(null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    public GenericMachineOperations getMachineOperations(String nomeMacchina){

        switch (nomeMacchina){
            case "bilancia":return bilanciaOperations;
            case "etichettatrice": return etichettatriceOperations;
            case "tappatrice": return tappatriceOperations;
            case "incartonatrice": return incartonatriceOperations;
            default: return null;
        }

    }

}
