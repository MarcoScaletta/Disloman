package it.unito.cassandraapiservice.model.repository.etichettatrice;

import it.unito.cassandraapiservice.model.impl.etichettatrice.RecordsEtichettatrice;
import org.springframework.data.repository.CrudRepository;

public interface RecordsEtichettatriceRepository
        extends CrudRepository<RecordsEtichettatrice, String> {
}
