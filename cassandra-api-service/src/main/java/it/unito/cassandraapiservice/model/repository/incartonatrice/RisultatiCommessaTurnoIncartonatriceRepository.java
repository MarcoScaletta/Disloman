package it.unito.cassandraapiservice.model.repository.incartonatrice;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessaTurno;
import it.unito.cassandraapiservice.model.impl.incartonatrice.RisultatiCommessaTurnoIncartonatrice;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiCommessaTurnoIncartonatriceRepository
        extends CrudRepository<RisultatiCommessaTurnoIncartonatrice, String> {

    @Query("SELECT * FROM risultati_incartonatrice_commessa_turno " + RisultatiCommessaTurno.WHERECONDITION)
    List<RisultatiCommessaTurnoIncartonatrice> findByCommessaAndTurno(
            @Param("commessa") String commessa,
            @Param("turno") String turno);
}
