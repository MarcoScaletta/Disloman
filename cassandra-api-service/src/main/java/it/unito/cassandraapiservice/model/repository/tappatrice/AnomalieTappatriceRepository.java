package it.unito.cassandraapiservice.model.repository.tappatrice;

import it.unito.cassandraapiservice.model.impl.tappatrice.AnomalieTappatrice;
import org.springframework.data.repository.CrudRepository;

public interface AnomalieTappatriceRepository
        extends CrudRepository<AnomalieTappatrice, String> {
}
