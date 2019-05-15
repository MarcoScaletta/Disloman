package it.unito.cassandraapiservice.model.repository;

import it.unito.cassandraapiservice.model.impl.tappatrice.RecordsTappatrice;
import org.springframework.data.repository.CrudRepository;

public interface RecordsTappatriceRepository extends CrudRepository<RecordsTappatrice, String> {
}
