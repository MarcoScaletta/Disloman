package it.unito.cassandraapiservice.model.repository.tappatrice;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessa;
import it.unito.cassandraapiservice.model.impl.tappatrice.RisultatiCommessaTappatrice;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RisultatiCommessaTappatriceRepository
        extends CrudRepository<RisultatiCommessaTappatrice, String> {

    @Query("SELECT * FROM risultati_tappatrice_commessa" + RisultatiCommessa.WHERECONDITION)
    List<RisultatiCommessaTappatrice> findByCommessa(
            @Param("commessa") String commessa);
}
