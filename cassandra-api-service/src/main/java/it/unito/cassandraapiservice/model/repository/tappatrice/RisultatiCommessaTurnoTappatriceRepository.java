package it.unito.cassandraapiservice.model.repository.tappatrice;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessaTurno;
import it.unito.cassandraapiservice.model.impl.tappatrice.RisultatiCommessaTurnoTappatrice;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiCommessaTurnoTappatriceRepository
        extends CrudRepository<RisultatiCommessaTurnoTappatrice, String> {

    @Query("SELECT * FROM risultati_tappatrice_commessa_turno " + RisultatiCommessaTurno.WHERECONDITION)
    List<RisultatiCommessaTurnoTappatrice> findByCommessaAndTurno(
            @Param("commessa") String commessa,
            @Param("turno") String turno);
}
