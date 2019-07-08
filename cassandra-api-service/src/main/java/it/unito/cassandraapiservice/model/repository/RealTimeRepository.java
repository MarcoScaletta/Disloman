package it.unito.cassandraapiservice.model.repository;

import it.unito.cassandraapiservice.model.impl.realtime.RealTime;
import org.springframework.data.repository.CrudRepository;

public interface RealTimeRepository
        extends CrudRepository<RealTime, String> {
}
