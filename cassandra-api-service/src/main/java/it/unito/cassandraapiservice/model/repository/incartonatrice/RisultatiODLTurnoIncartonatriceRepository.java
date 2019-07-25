package it.unito.cassandraapiservice.model.repository.incartonatrice;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiODLTurno;
import it.unito.cassandraapiservice.model.impl.incartonatrice.RisultatiODLTurnoIncartonatrice;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiODLTurnoIncartonatriceRepository
        extends CrudRepository<RisultatiODLTurnoIncartonatrice, String> {

    @Query("SELECT * FROM risultati_incartonatrice_odl_turno " + RisultatiODLTurno.WHERECONDITION)
    List<RisultatiODLTurnoIncartonatrice> findByODL(@Param("odl") String odl);
}
