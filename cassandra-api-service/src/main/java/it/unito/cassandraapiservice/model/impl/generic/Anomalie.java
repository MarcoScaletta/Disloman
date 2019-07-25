package it.unito.cassandraapiservice.model.impl.generic;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class Anomalie {

    @PrimaryKey("timestamp_inizio")
    private String timestampInizio;

    @Column("codice")
    private int codice;

    @Column("descrizione_anomalia")
    private String descrizioneAnomalia;

    @Column("timestamp_fine")
    private String timestampFine;
}
