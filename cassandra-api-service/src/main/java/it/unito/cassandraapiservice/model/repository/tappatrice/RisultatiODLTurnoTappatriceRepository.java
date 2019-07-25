package it.unito.cassandraapiservice.model.repository.tappatrice;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiODLTurno;
import it.unito.cassandraapiservice.model.impl.tappatrice.RisultatiODLTurnoTappatrice;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiODLTurnoTappatriceRepository
        extends CrudRepository<RisultatiODLTurnoTappatrice, String> {

    @Query("SELECT * FROM risultati_tappatrice_odl_turno " + RisultatiODLTurno.WHERECONDITION)
    List<RisultatiODLTurnoTappatrice> findByODL(@Param("odl") String odl);
}
