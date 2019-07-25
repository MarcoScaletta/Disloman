package it.unito.cassandraapiservice.model.impl.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class TempoCiclo{

    @JsonProperty("codice_prodotto")
    @PrimaryKey("codice_prodotto")
    private String codiceProdotto;

    @JsonProperty("timestamp")
    @Column("timestamp")
    private String timestamp;

    @JsonProperty("contatore")
    @Column("contatore")
    private int contatore;


    @JsonProperty("tempo_ciclo")
    @Column("tempo_ciclo")
    private float tempoCiclo;
}