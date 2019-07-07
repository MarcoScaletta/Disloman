package it.unito.cassandraapiservice.model.impl.generic;

import org.springframework.data.cassandra.core.mapping.Column;

public class GenericRecord {

    @Column("nome_prodotto")
    private String nomeProdotto;

    @Column("codice_prodotto")
    private String codiceProdotto;

    @Column("codice_commessa")
    private String codiceCommessa;

    @Column("turno")
    private String turno;

    @Column("codice_odl")
    private String cod_odl;
}
