package it.unito.cassandraapiservice.model.repository;

import it.unito.cassandraapiservice.model.impl.smartshareobjects.ClosedMonitor;
import org.springframework.data.repository.CrudRepository;


public interface ClosedMonitorRepository extends CrudRepository<ClosedMonitor, String> {
}
