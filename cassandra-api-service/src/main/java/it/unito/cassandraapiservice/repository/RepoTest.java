package it.unito.cassandraapiservice.repository;

import it.unito.cassandraapiservice.model.CassandraObject;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cassandraobjects", path = "cassandraobjects")
public interface RepoTest extends CassandraRepository<CassandraObject, String> {

}
