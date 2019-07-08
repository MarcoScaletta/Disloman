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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("api/risultati/{macchina}/")
    public RisultatiList getRisultati(@PathVariable String macchina){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return null;
        return machineOperations.getRisultati();
    }

    @GetMapping("api/risultati_commessa/{macchina}/{commessa}/")
    public RisultatiCommessaList getRisultatiCommessa(
            @PathVariable String macchina,
            @PathVariable String commessa){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return null;
        return machineOperations.getRisultatiCommessa(commessa);
    }

    @GetMapping("api/risultati_commessa_turno/{macchina}/{commessa}/{turno}/")
    public RisultatiCommessaTurnoList getRisultatiCommessaTurno(
            @PathVariable String macchina,
            @PathVariable String commessa,
            @PathVariable String turno){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return null;
        return machineOperations.getRisultatiCommessaTurno(commessa, turno);
    }

    @GetMapping("api/risultati_odl_turno/{macchina}/{odl}/{turno}/")
    public RisultatiODLTurnoList getRisultatiODLTurno(
            @PathVariable String macchina,
            @PathVariable String odl,
            @PathVariable String turno){
        GenericMachineOperations machineOperations = getMachineOperations(macchina);
        if (machineOperations == null)
            return null;
        return machineOperations.getRisultatiODLTurno(odl, turno);
    }

    @GetMapping("api/real_time/")
    public List<RealTime> getRealTime(){
        Iterable<RealTime> result = realTimeRepository.findAll();
        List<RealTime> realTimes = new ArrayList<>();
        result.forEach(realTimes::add);
        return realTimes;
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
