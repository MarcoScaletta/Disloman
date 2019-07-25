package it.unito.cassandraapiservice.model.repository;

import it.unito.cassandraapiservice.model.impl.smartshareobjects.OpenMonitor;
import org.springframework.data.repository.CrudRepository;


public interface OpenMonitorRepository extends CrudRepository<OpenMonitor, String> {
}
