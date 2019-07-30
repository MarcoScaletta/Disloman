package it.unito.cassandraapiservice.model.repository.incartonatrice;

import it.unito.cassandraapiservice.model.impl.incartonatrice.AnomalieIncartonatrice;
import org.springframework.data.repository.CrudRepository;

public interface AnomalieIncartonatriceRepository
        extends CrudRepository<AnomalieIncartonatrice, String> {
}
