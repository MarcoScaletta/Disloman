package it.unito.cassandraapiservice.model.machinepersistentoperations;

import it.unito.cassandraapiservice.controller.GenericMachineOperations;
import it.unito.cassandraapiservice.model.apiobjects.*;
import it.unito.cassandraapiservice.model.impl.generic.Anomalie;
import it.unito.cassandraapiservice.model.impl.generic.TempoCiclo;
import it.unito.cassandraapiservice.model.impl.tappatrice.*;
import it.unito.cassandraapiservice.model.impl.generic.Records;
import it.unito.cassandraapiservice.model.repository.tappatrice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

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

    @Autowired
    AnomalieTappatriceRepository anomalieTappatriceRepository;

    @Autowired
    TempoCicloTappatriceRepository tempoCicloTappatriceRepository;

    public RecordsList getRecords(){
        Iterable<RecordsTappatrice> result = repository.findAll();
        RecordsList recordsList = new RecordsList();
        result.forEach(recordsList.getRecordsList()::add);
        return recordsList;
    }

    public RecordsList addRecords(RecordsList recordsList){
        for (Records r : recordsList.getRecordsList())
            repository.save(new RecordsTappatrice(r));
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
    public RisultatiCommessaTurnoList getRisultatiCommessaTurno(String codiceCommessa) {
        Iterable<RisultatiCommessaTurnoTappatrice> result =
                risultatiCommessaTurnoTappatriceRepository.findByCommessa(codiceCommessa);
        RisultatiCommessaTurnoList risultatiCommessaTurnoList = new RisultatiCommessaTurnoList();
        result.forEach(risultatiCommessaTurnoList.getRisultatiCommessaTurnoList()::add);
        return risultatiCommessaTurnoList;
    }

    @Override
    public RisultatiODLTurnoList getRisultatiODLTurno(String codiceODL) {
        Iterable<RisultatiODLTurnoTappatrice> result =
                risultatiODLTurnoTappatriceRepository.findByODL(codiceODL);
        RisultatiODLTurnoList risultatiODLTurnoList = new RisultatiODLTurnoList();
        result.forEach(risultatiODLTurnoList.getRisultatiODLTurnoList()::add);
        return risultatiODLTurnoList;
    }


    @Override
    public AnomalieList getAnomalie() {
        Iterable<AnomalieTappatrice> result = anomalieTappatriceRepository.findAll();
        AnomalieList anomalieList = new AnomalieList();
        anomalieList.getRecordsList().addAll((Collection<? extends Anomalie>) result);
        return anomalieList;
    }

    @Override
    public TempoCicloList getTempoCiclo() {
        Iterable<TempoCicloTappatrice> result = tempoCicloTappatriceRepository.findAll();
        TempoCicloList tempoCicloList = new TempoCicloList();
        tempoCicloList.getRecordsList().addAll((Collection<? extends TempoCiclo>) result);
        return tempoCicloList;
    }
}
