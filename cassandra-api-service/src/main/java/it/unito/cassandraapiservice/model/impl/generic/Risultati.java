package it.unito.cassandraapiservice.model.impl.generic;

import lombok.Builder;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;

public class Risultati extends GenericRecord{

    @PrimaryKey
    PropertyKey key;

    @Column("timestamp")
    private String timestamp;

    @Column("oee_dinamico")
    private float oeeDinamico;

    @Column("oee_aggregato")
    private float oeeAggregato;


    @PrimaryKeyClass
    @Builder
    public static class PropertyKey implements Serializable {
        @PrimaryKeyColumn(name = "data",
                type = PrimaryKeyType.PARTITIONED)
        private String data;

        @PrimaryKeyColumn(name = "orario",
                type = PrimaryKeyType.CLUSTERED)
        private String orario;

        @Override
        public String toString(){
            return data+"_"+orario;
        }
    }
}
