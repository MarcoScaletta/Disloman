package it.unito.cassandraapiservice.model.machinepersistentoperations;

import it.unito.cassandraapiservice.controller.GenericMachineOperations;
import it.unito.cassandraapiservice.model.apiobjects.*;
import it.unito.cassandraapiservice.model.impl.bilancia.*;
import it.unito.cassandraapiservice.model.impl.generic.Anomalie;
import it.unito.cassandraapiservice.model.impl.generic.Records;
import it.unito.cassandraapiservice.model.impl.generic.TempoCiclo;
import it.unito.cassandraapiservice.model.repository.bilancia.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class BilanciaOperations implements GenericMachineOperations {

    @Autowired
    RecordsBilanciaRepository repository;

    @Autowired
    RisultatiBilanciaRepository risultatiBilanciaRepository;

    @Autowired
    RisultatiCommessaBilanciaRepository risultatiCommessaBilanciaRepository;

    @Autowired
    RisultatiCommessaTurnoBilanciaRepository risultatiCommessaTurnoBilanciaRepository;

    @Autowired
    RisultatiODLTurnoBilanciaRepository risultatiODLTurnoBilanciaRepository;

    @Autowired
    AnomalieBilanciaRepository anomalieBilanciaRepository;

    @Autowired
    TempoCicloBilanciaRepository tempoCicloBilanciaRepository;

    public RecordsList getRecords(){
        Iterable<RecordsBilancia> result = repository.findAll();

        RecordsList recordsList = new RecordsList();
        result.forEach(recordsList.getRecordsList()::add);
        return recordsList;
    }

    public RecordsList addRecords(RecordsList recordsList){
        for (Records r : recordsList.getRecordsList())
            repository.save(new RecordsBilancia(r));
        return recordsList;
    }

    public String deleteRecord(String id) {
        boolean result = repository.existsById(id);
        repository.deleteById(id);
        return "{ \"success\" : "+ (result ? "true" : "false") +" }";
    }

    @Override
    public RisultatiList getRisultati() {
        Iterable<RisultatiBilancia> result = risultatiBilanciaRepository.findAll();
        RisultatiList risultatiList = new RisultatiList();
        result.forEach(risultatiList.getRisultatiList()::add);
        return risultatiList;
    }

    @Override
    public RisultatiCommessaList getRisultatiCommessa(String codiceCommessa) {
        Iterable<RisultatiCommessaBilancia> result = risultatiCommessaBilanciaRepository.findByCommessa(codiceCommessa);
        RisultatiCommessaList risultatiCommessaList = new RisultatiCommessaList();
        result.forEach(risultatiCommessaList.getRisultatiCommessaList()::add);
        return risultatiCommessaList;
    }

    @Override
    public RisultatiCommessaTurnoList getRisultatiCommessaTurno(String codiceCommessa) {
        Iterable<RisultatiCommessaTurnoBilancia> result =
                risultatiCommessaTurnoBilanciaRepository.findByCommessa(codiceCommessa);
        RisultatiCommessaTurnoList risultatiCommessaTurnoList = new RisultatiCommessaTurnoList();
        result.forEach(risultatiCommessaTurnoList.getRisultatiCommessaTurnoList()::add);
        return risultatiCommessaTurnoList;
    }

    @Override
    public RisultatiODLTurnoList getRisultatiODLTurno(String codiceODL) {
        Iterable<RisultatiODLTurnoBilancia> result =
                risultatiODLTurnoBilanciaRepository.findByODL(codiceODL);
        RisultatiODLTurnoList risultatiODLTurnoList = new RisultatiODLTurnoList();
        result.forEach(risultatiODLTurnoList.getRisultatiODLTurnoList()::add);
        return risultatiODLTurnoList;
    }

    @Override
    public AnomalieList getAnomalie() {
        Iterable<AnomalieBilancia> result = anomalieBilanciaRepository.findAll();
        AnomalieList anomalieList = new AnomalieList();
        anomalieList.getRecordsList().addAll((Collection<? extends Anomalie>) result);
        return anomalieList;
    }

    @Override
    public TempoCicloList getTempoCiclo() {
        Iterable<TempoCicloBilancia> result = tempoCicloBilanciaRepository.findAll();
        TempoCicloList tempoCicloList = new TempoCicloList();
        tempoCicloList.getRecordsList().addAll((Collection<? extends TempoCiclo>) result);
        return tempoCicloList;
    }


}
