package it.unito.cassandraapiservice.model.impl.generic;

import lombok.Builder;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

public class RisultatiCommessa {

    @PrimaryKey
    PropertyKey key;

    @Column("nome_prodotto")
    private float nomeProdotto;

    @Column("oee_statico")
    private float oeeStatico;


    @PrimaryKeyClass
    @Builder
    private static class PropertyKey implements Serializable {

        @PrimaryKeyColumn(name = "codice_commessa",
                type = PrimaryKeyType.CLUSTERED)
        private String codiceCommessa;

        @PrimaryKeyColumn(name = "codice_prodotto",
                type = PrimaryKeyType.CLUSTERED)
        private String codiceProdotto;

        @PrimaryKeyColumn(name = "data",
                type = PrimaryKeyType.PARTITIONED)
        private String data;

        @PrimaryKeyColumn(name = "turno",
                type = PrimaryKeyType.CLUSTERED)
        private String turno;

    }
}
