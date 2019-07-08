package it.unito.cassandraapiservice.model.machinepersistentoperations;

import it.unito.cassandraapiservice.controller.GenericMachineOperations;
import it.unito.cassandraapiservice.model.apiobjects.*;
import it.unito.cassandraapiservice.model.impl.tappatrice.*;
import it.unito.cassandraapiservice.model.impl.generic.Records;
import it.unito.cassandraapiservice.model.repository.tappatrice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TappatriceOperations implements GenericMachineOperations {

    @Autowired
    RecordsTappatriceRepository repository;

    @Autowired
    RisultatiTappatriceRepository risultatiTappatriceRepository;

    @Autowired
    RisultatiCommessaTappatriceRepository risultatiCommessaTappatriceRepository;

    @Autowired
    RisultatiCommessaTurnoTappatriceRepository risultatiCommessaTurnoTappatriceRepository;

    @Autowired
    RisultatiODLTurnoTappatriceRepository risultatiODLTurnoTappatriceRepository;

    public RecordsList getRecords(){
        Iterable<RecordsTappatrice> result = repository.findAll();
        RecordsList recordsList = new RecordsList();
        result.forEach(recordsList.getRecordsList()::add);
        return recordsList;
    }

    public RecordsList addRecords(RecordsList recordsList){
        for (Records r : recordsList.getRecordsList())
            repository.save((RecordsTappatrice) r);
        return recordsList;
    }

    public String deleteRecord(String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }

    @Override
    public RisultatiList getRisultati() {
        Iterable<RisultatiTappatrice> result = risultatiTappatriceRepository.findAll();
        RisultatiList risultatiList = new RisultatiList();
        result.forEach(risultatiList.getRisultatiList()::add);
        return risultatiList;
    }

    @Override
    public RisultatiCommessaList getRisultatiCommessa(String codiceCommessa) {
        Iterable<RisultatiCommessaTappatrice> result =
                risultatiCommessaTappatriceRepository.findByCommessa(codiceCommessa);
        RisultatiCommessaList risultatiCommessaList = new RisultatiCommessaList();
        result.forEach(risultatiCommessaList.getRisultatiCommessaList()::add);
        return risultatiCommessaList;
    }

    @Override
    public RisultatiCommessaTurnoList getRisultatiCommessaTurno(String codiceCommessa, String turno) {
        Iterable<RisultatiCommessaTurnoTappatrice> result =
                risultatiCommessaTurnoTappatriceRepository.findByCommessaAndTurno(codiceCommessa, turno);
        RisultatiCommessaTurnoList risultatiCommessaTurnoList = new RisultatiCommessaTurnoList();
        result.forEach(risultatiCommessaTurnoList.getRisultatiCommessaTurnoList()::add);
        return risultatiCommessaTurnoList;
    }

    @Override
    public RisultatiODLTurnoList getRisultatiODLTurno(String codiceODL, String turno) {
        Iterable<RisultatiODLTurnoTappatrice> result =
                risultatiODLTurnoTappatriceRepository.findByODLAndCommessa(codiceODL,turno);
        RisultatiODLTurnoList risultatiODLTurnoList = new RisultatiODLTurnoList();
        result.forEach(risultatiODLTurnoList.getRisultatiODLTurnoList()::add);
        return risultatiODLTurnoList;
    }
}
