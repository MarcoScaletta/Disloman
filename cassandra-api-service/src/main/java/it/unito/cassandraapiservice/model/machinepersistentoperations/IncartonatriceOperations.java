package it.unito.cassandraapiservice.model.machinepersistentoperations;

import it.unito.cassandraapiservice.controller.GenericMachineOperations;
import it.unito.cassandraapiservice.model.apiobjects.*;
import it.unito.cassandraapiservice.model.impl.incartonatrice.*;
import it.unito.cassandraapiservice.model.impl.generic.Records;
import it.unito.cassandraapiservice.model.repository.incartonatrice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncartonatriceOperations implements GenericMachineOperations {

    @Autowired
    RecordsIncartonatriceRepository repository;

    @Autowired
    RisultatiIncartonatriceRepository risultatiIncartonatriceRepository;

    @Autowired
    RisultatiCommessaIncartonatriceRepository risultatiCommessaIncartonatriceRepository;

    @Autowired
    RisultatiCommessaTurnoIncartonatriceRepository risultatiCommessaTurnoIncartonatriceRepository;

    @Autowired
    RisultatiODLTurnoIncartonatriceRepository risultatiODLTurnoIncartonatriceRepository;

    public RecordsList getRecords(){
        Iterable<RecordsIncartonatrice> result = repository.findAll();
        RecordsList recordsList = new RecordsList();
        result.forEach(recordsList.getRecordsList()::add);
        return recordsList;
    }

    public RecordsList addRecords(RecordsList recordsList){
        for (Records r : recordsList.getRecordsList())
            repository.save(new RecordsIncartonatrice(r));
        return recordsList;
    }

    public String deleteRecord(String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }

    @Override
    public RisultatiList getRisultati() {
        Iterable<RisultatiIncartonatrice> result = risultatiIncartonatriceRepository.findAll();
        RisultatiList risultatiList = new RisultatiList();
        result.forEach(risultatiList.getRisultatiList()::add);
        return risultatiList;
    }

    @Override
    public RisultatiCommessaList getRisultatiCommessa(String codiceCommessa) {
        Iterable<RisultatiCommessaIncartonatrice> result =
                risultatiCommessaIncartonatriceRepository.findByCommessa(codiceCommessa);
        RisultatiCommessaList risultatiCommessaList = new RisultatiCommessaList();
        result.forEach(risultatiCommessaList.getRisultatiCommessaList()::add);
        return risultatiCommessaList;
    }

    @Override
    public RisultatiCommessaTurnoList getRisultatiCommessaTurno(String codiceCommessa) {
        Iterable<RisultatiCommessaTurnoIncartonatrice> result
                = risultatiCommessaTurnoIncartonatriceRepository.findByCommessa(codiceCommessa);
        RisultatiCommessaTurnoList risultatiCommessaTurnoList = new RisultatiCommessaTurnoList();
        result.forEach(risultatiCommessaTurnoList.getRisultatiCommessaTurnoList()::add);
        return risultatiCommessaTurnoList;
    }

    @Override
    public RisultatiODLTurnoList getRisultatiODLTurno(String codiceODL) {
        Iterable<RisultatiODLTurnoIncartonatrice> result =
                risultatiODLTurnoIncartonatriceRepository.findByODL(codiceODL);
        RisultatiODLTurnoList risultatiODLTurnoList = new RisultatiODLTurnoList();
        result.forEach(risultatiODLTurnoList.getRisultatiODLTurnoList()::add);
        return risultatiODLTurnoList;
    }
}
