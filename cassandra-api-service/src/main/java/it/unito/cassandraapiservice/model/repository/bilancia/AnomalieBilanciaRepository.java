package it.unito.cassandraapiservice.model.repository.bilancia;

import it.unito.cassandraapiservice.model.impl.bilancia.AnomalieBilancia;
import it.unito.cassandraapiservice.model.impl.bilancia.RecordsBilancia;
import org.springframework.data.repository.CrudRepository;

public interface AnomalieBilanciaRepository
        extends CrudRepository<AnomalieBilancia, String> {
}
