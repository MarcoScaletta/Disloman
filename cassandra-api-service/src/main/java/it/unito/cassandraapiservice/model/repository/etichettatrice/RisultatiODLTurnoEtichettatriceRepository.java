package it.unito.cassandraapiservice.model.repository.etichettatrice;

import it.unito.cassandraapiservice.model.impl.etichettatrice.RisultatiODLTurnoEtichettatrice;
import it.unito.cassandraapiservice.model.impl.generic.RisultatiODLTurno;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiODLTurnoEtichettatriceRepository
        extends CrudRepository<RisultatiODLTurnoEtichettatrice, String> {

    @Query("SELECT * FROM risultati_etichettatrice_odl_turno " + RisultatiODLTurno.WHERECONDITION)
    List<RisultatiODLTurnoEtichettatrice> findByODL(@Param("odl") String odl);
}
