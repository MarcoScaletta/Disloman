package it.unito.cassandraapiservice.model.repository.etichettatrice;

import it.unito.cassandraapiservice.model.impl.etichettatrice.RisultatiCommessaEtichettatrice;
import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessa;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiCommessaEtichettatriceRepository
        extends CrudRepository<RisultatiCommessaEtichettatrice, String> {

    @Query("SELECT * FROM risultati_etichettatrice_commessa" + RisultatiCommessa.WHERECONDITION)
    List<RisultatiCommessaEtichettatrice> findByCommessa(
            @Param("commessa") String commessa);
}
