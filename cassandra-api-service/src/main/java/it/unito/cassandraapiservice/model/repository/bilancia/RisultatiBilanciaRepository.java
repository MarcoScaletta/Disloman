package it.unito.cassandraapiservice.model.repository.bilancia;

import it.unito.cassandraapiservice.model.impl.bilancia.RisultatiBilancia;
import org.springframework.data.repository.CrudRepository;

public interface RisultatiBilanciaRepository
        extends CrudRepository<RisultatiBilancia, String> {
}
