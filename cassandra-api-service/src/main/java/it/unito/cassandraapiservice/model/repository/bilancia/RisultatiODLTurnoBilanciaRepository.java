package it.unito.cassandraapiservice.model.repository.bilancia;

import it.unito.cassandraapiservice.model.impl.bilancia.RisultatiODLTurnoBilancia;
import it.unito.cassandraapiservice.model.impl.generic.RisultatiODLTurno;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiODLTurnoBilanciaRepository
        extends CrudRepository<RisultatiODLTurnoBilancia, String> {

    @Query("SELECT * FROM risultati_bilancia_odl_turno " + RisultatiODLTurno.WHERECONDITION)
    List<RisultatiODLTurnoBilancia> findByODLAndCommessa(
            @Param("odl") String odl,
            @Param("turno") String turno);

}
