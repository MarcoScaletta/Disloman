package it.unito.cassandraapiservice.controller;

import it.unito.cassandraapiservice.model.apiobjects.*;

public interface GenericMachineOperations {

    RecordsList getRecords();

    RecordsList addRecords(RecordsList records);

    String deleteRecord(String id);

    RisultatiList getRisultati();

    RisultatiCommessaList getRisultatiCommessa(String codiceCommessa);

    RisultatiCommessaTurnoList getRisultatiCommessaTurno(String codiceCommessa, String turno);

    RisultatiODLTurnoList getRisultatiODLTurno(String codiceODL, String turno);

}
