package it.unito.cassandraapiservice.model.machinepersistentoperations;

import it.unito.cassandraapiservice.controller.GenericMachineOperations;
import it.unito.cassandraapiservice.model.apiobjects.*;
import it.unito.cassandraapiservice.model.impl.etichettatrice.*;
import it.unito.cassandraapiservice.model.impl.generic.Records;
import it.unito.cassandraapiservice.model.repository.etichettatrice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EtichettatriceOperations implements GenericMachineOperations {

    @Autowired
    RecordsEtichettatriceRepository repository;

    @Autowired
    RisultatiEtichettatriceRepository risultatiEtichettatriceRepository;

    @Autowired
    RisultatiCommessaEtichettatriceRepository risultatiCommessaEtichettatriceRepository;

    @Autowired
    RisultatiCommessaTurnoEtichettatriceRepository risultatiCommessaTurnoEtichettatriceRepository;

    @Autowired
    RisultatiODLTurnoEtichettatriceRepository risultatiODLTurnoEtichettatriceRepository;

    public RecordsList getRecords(){
        Iterable<RecordsEtichettatrice> result = repository.findAll();
        RecordsList recordsList = new RecordsList();
        result.forEach(recordsList.getRecordsList()::add);
        return recordsList;
    }

    public RecordsList addRecords(RecordsList recordsList){
        for (Records r : recordsList.getRecordsList())
            repository.save((RecordsEtichettatrice) r);
        return recordsList;
    }

    public String deleteRecord(String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }

    @Override
    public RisultatiList getRisultati() {
        Iterable<RisultatiEtichettatrice> result = risultatiEtichettatriceRepository.findAll();
        RisultatiList risultatiList = new RisultatiList();
        result.forEach(risultatiList.getRisultatiList()::add);
        return risultatiList;
    }

    @Override
    public RisultatiCommessaList getRisultatiCommessa(String codiceCommessa) {
        Iterable<RisultatiCommessaEtichettatrice> result = risultatiCommessaEtichettatriceRepository.findAll();
        RisultatiCommessaList risultatiCommessaList = new RisultatiCommessaList();
        result.forEach(risultatiCommessaList.getRisultatiCommessaList()::add);
        return risultatiCommessaList;
    }

    @Override
    public RisultatiCommessaTurnoList getRisultatiCommessaTurno(String codiceCommessa, String turno) {
        Iterable<RisultatiCommessaTurnoEtichettatrice> result =
                risultatiCommessaTurnoEtichettatriceRepository.findByCommessaAndTurno(codiceCommessa, turno);
        RisultatiCommessaTurnoList risultatiCommessaTurnoList = new RisultatiCommessaTurnoList();
        result.forEach(risultatiCommessaTurnoList.getRisultatiCommessaTurnoList()::add);
        return risultatiCommessaTurnoList;
    }

    @Override
    public RisultatiODLTurnoList getRisultatiODLTurno(String codiceODL, String turno) {
        Iterable<RisultatiODLTurnoEtichettatrice> result =
                risultatiODLTurnoEtichettatriceRepository.findByODLAndCommessa(codiceODL,turno);
        RisultatiODLTurnoList risultatiODLTurnoList = new RisultatiODLTurnoList();
        result.forEach(risultatiODLTurnoList.getRisultatiODLTurnoList()::add);
        return risultatiODLTurnoList;
    }
}
