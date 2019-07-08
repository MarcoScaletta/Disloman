package it.unito.cassandraapiservice.model.repository.bilancia;

import it.unito.cassandraapiservice.model.impl.bilancia.RisultatiCommessaBilancia;
import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessa;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiCommessaBilanciaRepository
        extends CrudRepository<RisultatiCommessaBilancia, String> {

    @Query("SELECT * FROM risultati_bilancia_commessa" + RisultatiCommessa.WHERECONDITION)
    List<RisultatiCommessaBilancia> findByCommessa(
            @Param("commessa") String commessa);
}
