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

public class RisultatiCommessa {

    public static final String WHERECONDITION = " WHERE codice_commessa=?0 ALLOW FILTERING ";

    @JsonProperty("key")
    @PrimaryKey
    PropertyKey key;

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

        @JsonProperty("codice_commessa")
        @PrimaryKeyColumn(name = "codice_commessa",
                type = PrimaryKeyType.CLUSTERED)
        private String codiceCommessa;

        @JsonProperty("codice_prodotto")
        @PrimaryKeyColumn(name = "codice_prodotto",
                type = PrimaryKeyType.PARTITIONED)
        private String codiceProdotto;

    }

    public  static String query(String nomeMacchina){
        return "SELECT * FROM risultati_" +
                "bilancia_" + nomeMacchina + " " +
                "WHERE codice_commessa=?0 turno=?1 ALLOW FILTERING";
    }
}
