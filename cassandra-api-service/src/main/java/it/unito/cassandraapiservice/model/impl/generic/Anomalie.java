package it.unito.cassandraapiservice.model.impl.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class Anomalie {

    @JsonProperty("timestamp_inizio")
    @PrimaryKey("timestamp_inizio")
    private String timestampInizio;

    @JsonProperty("codice")
    @Column("codice")
    private int codice;

    @JsonProperty("descrizione_anomalia")
    @Column("descrizione_anomalia")
    private String descrizioneAnomalia;

    @JsonProperty("timestamp_fine")
    @Column("timestamp_fine")
    private String timestampFine;
}
