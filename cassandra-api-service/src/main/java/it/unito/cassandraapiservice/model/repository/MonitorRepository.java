package it.unito.cassandraapiservice.model.repository;

import it.unito.cassandraapiservice.model.impl.smartshareobjects.Monitor;
import org.springframework.data.repository.CrudRepository;


public interface MonitorRepository extends CrudRepository<Monitor, String> {
}
