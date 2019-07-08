package it.unito.cassandraapiservice.model.repository.bilancia;

import it.unito.cassandraapiservice.model.impl.bilancia.RisultatiCommessaTurnoBilancia;
import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessaTurno;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiCommessaTurnoBilanciaRepository
        extends CrudRepository<RisultatiCommessaTurnoBilancia, String> {

    @Query("SELECT * FROM risultati_bilancia_commessa_turno " + RisultatiCommessaTurno.WHERECONDITION)
    List<RisultatiCommessaTurnoBilancia> findByCommessaAndTurno(
            @Param("commessa") String commessa,
            @Param("turno") String turno);
}
