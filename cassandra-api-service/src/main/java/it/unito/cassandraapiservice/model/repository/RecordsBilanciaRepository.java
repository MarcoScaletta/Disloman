package it.unito.cassandraapiservice.model.repository;

import it.unito.cassandraapiservice.model.impl.bilancia.RecordsBilancia;
import org.springframework.data.repository.CrudRepository;

public interface RecordsBilanciaRepository
        extends CrudRepository<RecordsBilancia, String> {
}
