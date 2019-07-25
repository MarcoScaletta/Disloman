package it.unito.cassandraapiservice.controller;


import it.unito.cassandraapiservice.model.apiobjects.*;
import it.unito.cassandraapiservice.model.impl.realtime.RealTime;
import it.unito.cassandraapiservice.model.machinepersistentoperations.BilanciaOperations;
import it.unito.cassandraapiservice.model.machinepersistentoperations.EtichettatriceOperations;
import it.unito.cassandraapiservice.model.machinepersistentoperations.IncartonatriceOperations;
import it.unito.cassandraapiservice.model.machinepersistentoperations.TappatriceOperations;
import it.unito.cassandraapiservice.model.repository.RealTimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public RecordsList getRecords(@PathVariable String macchina){
        return getMachineOperations(macchina).getRecords();
    }

    @PostMapping("/records/{macchina}/")
    public RecordsList addRecords(@PathVariable String macchina,
            @RequestBody RecordsList records){
        return getMachineOperations(macchina).addRecords(records);
    }


    @DeleteMapping(value = "/records/{macchina}/{id}/",
            produces = "application/json; charset=utf-8")
    public String deleteRecord(@PathVariable String macchina, @PathVariable String id) {
        return getMachineOperations(macchina).deleteRecord(id);
    }


    // EXTERNAL API

    @GetMapping("api/risultati/macchina/{macchina}/")
    public ResponseEntity<RisultatiList> getRisultati(@PathVariable String macchina){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok(machineOperations.getRisultati());
    }

    @GetMapping("api/risultati/macchina/{macchina}/commessa/{commessa}/")
    public ResponseEntity<RisultatiCommessaList> getRisultatiCommessa(
            @PathVariable String macchina,
            @PathVariable String commessa){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok(machineOperations.getRisultatiCommessa(commessa));
    }

    @GetMapping("api/risultati_turno/macchina/{macchina}/commessa/{commessa}/")
    public ResponseEntity<RisultatiCommessaTurnoList> getRisultatiCommessaTurno(
            @PathVariable String macchina,
            @PathVariable String commessa){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok(machineOperations.getRisultatiCommessaTurno(commessa));
    }

    @GetMapping("api/risultati_turno/macchina/{macchina}/odl/{odl}/")
    public ResponseEntity<RisultatiODLTurnoList> getRisultatiODLTurno(
            @PathVariable String macchina,
            @PathVariable String odl){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok(machineOperations.getRisultatiODLTurno(odl));
    }

    @GetMapping("api/real_time/macchina/{macchina}/")
    public ResponseEntity<RealTime> getRealTimeMacchina(@PathVariable String macchina){
        Optional<RealTime> result = realTimeRepository.findById(macchina);
        return result.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.status(404).body(null));
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
