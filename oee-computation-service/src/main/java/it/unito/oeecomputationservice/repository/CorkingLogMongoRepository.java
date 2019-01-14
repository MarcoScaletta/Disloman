package it.unito.oeecomputationservice.repository;

import it.unito.oeecomputationservice.events.model.CorkingLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "corkinglogs", path = "corkinglogs")
public interface CorkingLogMongoRepository extends MongoRepository<CorkingLog, String> {
}
