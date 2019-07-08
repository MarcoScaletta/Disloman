package it.unito.cassandraapiservice.model.impl.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;

public class Risultati extends GenericRecord{

    @JsonProperty("key")
    @PrimaryKey
    PropertyKey key;

    @JsonProperty("timestamp")
    @Column("timestamp")
    private String timestamp;

    @JsonProperty("oee_dinamico")
    @Column("oee_dinamico")
    private float oeeDinamico;

    @JsonProperty("oee_aggregato")
    @Column("oee_aggregato")
    private float oeeAggregato;


    @PrimaryKeyClass
    @Builder
    public static class PropertyKey implements Serializable {

        @JsonProperty("data")
        @PrimaryKeyColumn(name = "data",
                type = PrimaryKeyType.PARTITIONED)
        private String data;

        @JsonProperty("orario")
        @PrimaryKeyColumn(name = "orario",
                type = PrimaryKeyType.CLUSTERED)
        private String orario;

        @Override
        public String toString(){
            return data+"_"+orario;
        }
    }
}
