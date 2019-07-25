package it.unito.cassandraapiservice.model.impl.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class Records extends GenericRecord{

    public Records(){}

    public Records(Records records){
        this.pezziProdotti = records.pezziProdotti;
        this.time = records.time;
        this.cod_odl =records.cod_odl;
        this.codiceCommessa =records.codiceCommessa;
        this.codiceProdotto =records.codiceProdotto;
        this.nomeProdotto =records.nomeProdotto;
        this.turno =records.turno;
    }

    @JsonProperty("time")
    @PrimaryKey("time")
    private String time;

    @JsonProperty("pezzi_prodotti")
    @Column("pezzi_prodotti")
    private int pezziProdotti;

}
