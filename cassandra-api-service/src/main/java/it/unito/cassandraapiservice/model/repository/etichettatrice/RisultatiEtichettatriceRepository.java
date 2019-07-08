package it.unito.cassandraapiservice.model.repository.etichettatrice;

import it.unito.cassandraapiservice.model.impl.etichettatrice.RisultatiEtichettatrice;
import org.springframework.data.repository.CrudRepository;

public interface RisultatiEtichettatriceRepository
        extends CrudRepository<RisultatiEtichettatrice, String> {
}
