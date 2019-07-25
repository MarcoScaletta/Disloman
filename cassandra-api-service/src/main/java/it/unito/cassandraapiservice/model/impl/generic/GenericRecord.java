package it.unito.cassandraapiservice.model.impl.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.cassandra.core.mapping.Column;

public class GenericRecord {

    @JsonProperty("nome_prodotto")
    @Column("nome_prodotto")
    protected String nomeProdotto;

    @JsonProperty("codice_prodotto")
    @Column("codice_prodotto")
    protected String codiceProdotto;

    @JsonProperty("codice_commessa")
    @Column("codice_commessa")
    protected String codiceCommessa;

    @JsonProperty("turno")
    @Column("turno")
    protected String turno;

    @JsonProperty("codice_odl")
    @Column("codice_odl")
    protected String cod_odl;
}
