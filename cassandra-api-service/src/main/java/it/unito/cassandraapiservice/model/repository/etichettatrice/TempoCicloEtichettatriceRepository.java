package it.unito.cassandraapiservice.model.repository.etichettatrice;

import it.unito.cassandraapiservice.model.impl.bilancia.TempoCicloBilancia;
import it.unito.cassandraapiservice.model.impl.etichettatrice.TempoCicloEtichettatrice;
import org.springframework.data.repository.CrudRepository;

public interface TempoCicloEtichettatriceRepository
        extends CrudRepository<TempoCicloEtichettatrice, String> {
}
