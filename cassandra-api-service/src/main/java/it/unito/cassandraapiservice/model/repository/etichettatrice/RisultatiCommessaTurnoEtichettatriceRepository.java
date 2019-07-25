package it.unito.cassandraapiservice.model.repository.etichettatrice;

import it.unito.cassandraapiservice.model.impl.etichettatrice.RisultatiCommessaTurnoEtichettatrice;
import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessaTurno;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiCommessaTurnoEtichettatriceRepository
        extends CrudRepository<RisultatiCommessaTurnoEtichettatrice, String> {

    @Query("SELECT * FROM risultati_etichettatrice_commessa_turno " + RisultatiCommessaTurno.WHERECONDITION)
    List<RisultatiCommessaTurnoEtichettatrice> findByCommessa(@Param("commessa") String commessa);
}
