package it.unito.cassandraapiservice.model.repository.etichettatrice;

import it.unito.cassandraapiservice.model.impl.etichettatrice.AnomalieEtichettatrice;
import org.springframework.data.repository.CrudRepository;

public interface AnomalieEtichettatriceRepository
        extends CrudRepository<AnomalieEtichettatrice, String> {
}
