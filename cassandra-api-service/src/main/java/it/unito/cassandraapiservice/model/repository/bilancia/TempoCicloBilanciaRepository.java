package it.unito.cassandraapiservice.model.repository.bilancia;

import it.unito.cassandraapiservice.model.impl.bilancia.TempoCicloBilancia;
import org.springframework.data.repository.CrudRepository;

public interface TempoCicloBilanciaRepository
        extends CrudRepository<TempoCicloBilancia, String> {
}
