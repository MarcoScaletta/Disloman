package it.unito.cassandraapiservice.model.impl.generic;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class TempoCiclo{

    @PrimaryKey("codice_prodotto")
    private String codiceProdotto;

    @Column("timestamp")
    private String timestamp;

    @Column("contatore")
    private int contatore;
}