package it.unito.cassandraapiservice.model.repository.incartonatrice;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessa;
import it.unito.cassandraapiservice.model.impl.incartonatrice.RisultatiCommessaIncartonatrice;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiCommessaIncartonatriceRepository
        extends CrudRepository<RisultatiCommessaIncartonatrice, String> {

    @Query("SELECT * FROM risultati_incartonatrice_commessa" + RisultatiCommessa.WHERECONDITION)
    List<RisultatiCommessaIncartonatrice> findByCommessa(
            @Param("commessa") String commessa);
}
