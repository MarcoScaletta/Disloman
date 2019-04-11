package it.unito.cassandraapiservice.model.repository;


import it.unito.cassandraapiservice.model.impl.astucciatrice.RecordsAstucciatrice;
import it.unito.cassandraapiservice.model.impl.astucciatrice.RisultatiAstucciatrice;
import it.unito.cassandraapiservice.model.impl.astucciatrice.TempoCicloAstucciatrice;

import it.unito.cassandraapiservice.model.impl.etichettatrice.RecordsEtichettatrice;
import it.unito.cassandraapiservice.model.impl.etichettatrice.RisultatiEtichettatrice;
import it.unito.cassandraapiservice.model.impl.etichettatrice.TempoCicloEtichettatrice;

import it.unito.cassandraapiservice.model.impl.tappatrice.RecordsTappatrice;
import it.unito.cassandraapiservice.model.impl.tappatrice.RisultatiTappatrice;
import it.unito.cassandraapiservice.model.impl.tappatrice.TempoCicloTappatrice;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// ETICHETTATRICE

@RepositoryRestResource(
        collectionResourceRel = "records_etichettatrice",
        path = "records_etichettatrice")
interface RepoRecordsEtichettatrice extends CassandraRepository<RecordsEtichettatrice,String> {
}

@RepositoryRestResource(
        collectionResourceRel = "risultati_etichettatrice",
        path = "risultati_etichettatrice")
interface RepoRisultatiEtichettatrice extends CassandraRepository<RisultatiEtichettatrice,String> {
}

@RepositoryRestResource(
        collectionResourceRel = "tempo_ciclo_etichettatrice",
        path = "tempo_ciclo_etichettatrice")
interface RepoTempoCicloEtichettatrice extends CassandraRepository<TempoCicloEtichettatrice,String> {
}

// ASTUCCIATRICE

@RepositoryRestResource(
        collectionResourceRel = "records_astucciatrice",
        path = "records_astucciatrice")
interface RepoRecordsAstucciatrice extends CassandraRepository<RecordsAstucciatrice,String> {
}

@RepositoryRestResource(
        collectionResourceRel = "risultati_astucciatrice",
        path = "risultati_astucciatrice")
interface RepoRisultatiAstucciatrice extends CassandraRepository<RisultatiAstucciatrice,String> {
}

@RepositoryRestResource(
        collectionResourceRel = "tempo_ciclo_astucciatrice",
        path = "tempo_ciclo_astucciatrice")
interface RepoTempoCicloAstucciatrice extends CassandraRepository<TempoCicloAstucciatrice,String> {
}

// TAPPATRICE

@RepositoryRestResource(
        collectionResourceRel = "records_tappatrice",
        path = "records_tappatrice")
interface RepoRecordsTappatrice extends CassandraRepository<RecordsTappatrice,String> {
}

@RepositoryRestResource(
        collectionResourceRel = "risultati_tappatrice",
        path = "risultati_tappatrice")
interface RepoRisultatiTappatrice extends CassandraRepository<RisultatiTappatrice,String> {
}

@RepositoryRestResource(
        collectionResourceRel = "tempo_ciclo_tappatrice",
        path = "tempo_ciclo_tappatrice")
interface RepoTempoCicloTappatrice extends CassandraRepository<TempoCicloTappatrice,String> {
}