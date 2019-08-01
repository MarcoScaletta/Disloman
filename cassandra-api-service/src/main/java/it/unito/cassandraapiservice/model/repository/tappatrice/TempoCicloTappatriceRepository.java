package it.unito.cassandraapiservice.model.repository.tappatrice;

import it.unito.cassandraapiservice.model.impl.etichettatrice.TempoCicloEtichettatrice;
import it.unito.cassandraapiservice.model.impl.tappatrice.TempoCicloTappatrice;
import org.springframework.data.repository.CrudRepository;

public interface TempoCicloTappatriceRepository
        extends CrudRepository<TempoCicloTappatrice, String> {
}
