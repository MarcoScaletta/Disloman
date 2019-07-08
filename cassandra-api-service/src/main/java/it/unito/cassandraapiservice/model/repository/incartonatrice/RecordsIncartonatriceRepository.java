package it.unito.cassandraapiservice.model.repository.incartonatrice;

import it.unito.cassandraapiservice.model.impl.incartonatrice.RecordsIncartonatrice;
import org.springframework.data.repository.CrudRepository;

public interface RecordsIncartonatriceRepository
        extends CrudRepository<RecordsIncartonatrice, String> {
}
