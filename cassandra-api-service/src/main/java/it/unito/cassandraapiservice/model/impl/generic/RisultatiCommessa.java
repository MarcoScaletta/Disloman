package it.unito.cassandraapiservice.model.impl.generic;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Builder;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

public class RisultatiCommessa {

    public static final String WHERECONDITION = " WHERE codice_commessa=?0 ALLOW FILTERING ";

    @PrimaryKey
    private PropertyKey key;

    @JsonProperty("data_inizio")
    @Column("data_inizio")
    private String dataInizio;

    @JsonProperty("data_fine")
    @Column("data_fine")
    private String dataFine;

    @JsonProperty("nome_prodotto")
    @Column("nome_prodotto")
    private String nomeProdotto;

    @JsonProperty("oee_statico")
    @Column("oee_statico")
    private float oeeStatico;



    @PrimaryKeyClass
    @Builder
    private static class PropertyKey implements Serializable {

        @PrimaryKeyColumn(name = "codice_prodotto",
                type = PrimaryKeyType.PARTITIONED)
        private String codiceProdotto;

        @PrimaryKeyColumn(name = "codice_commessa",
                type = PrimaryKeyType.CLUSTERED)
        private String codiceCommessa;

    }

    @JsonGetter("codice_commessa")
    public String getCodiceCommessa(){
        return key.codiceCommessa;
    }
    @JsonGetter("codice_prodotto")
    public String getCodiceProdotto(){
        return key.codiceProdotto;
    }
}
