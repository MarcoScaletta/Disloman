package it.unito.cassandraapiservice.model.repository.incartonatrice;

import it.unito.cassandraapiservice.model.impl.etichettatrice.TempoCicloEtichettatrice;
import it.unito.cassandraapiservice.model.impl.incartonatrice.TempoCicloIncartonatrice;
import org.springframework.data.repository.CrudRepository;

public interface TempoCicloIncartonatriceRepository
        extends CrudRepository<TempoCicloIncartonatrice, String> {
}
