package it.unito.cassandraapiservice.model.impl.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

public class RisultatiODLTurno {

    public static final String WHERECONDITION = " WHERE codice_odl=?0 AND turno=?1 ALLOW FILTERING ";

    @JsonProperty("key")
    @PrimaryKey
    PropertyKey key;

    @JsonProperty("nome_prodotto")
    @Column("nome_prodotto")
    private String nomeProdotto;

    @JsonProperty("oee_statico")
    @Column("oee_statico")
    private float oeeStatico;


    @PrimaryKeyClass
    @Builder
    private static class PropertyKey implements Serializable {

        @JsonProperty("codice_odl")
        @PrimaryKeyColumn(name = "codice_odl",
                type = PrimaryKeyType.CLUSTERED)
        private String codiceODL;

        @JsonProperty("codice_prodotto")
        @PrimaryKeyColumn(name = "codice_prodotto",
                type = PrimaryKeyType.CLUSTERED)
        private String codiceProdotto;

        @JsonProperty("data")
        @PrimaryKeyColumn(name = "data",
                type = PrimaryKeyType.PARTITIONED)
        private String data;

        @JsonProperty("turno")
        @PrimaryKeyColumn(name = "turno",
                type = PrimaryKeyType.CLUSTERED)
        private String turno;

    }
}
