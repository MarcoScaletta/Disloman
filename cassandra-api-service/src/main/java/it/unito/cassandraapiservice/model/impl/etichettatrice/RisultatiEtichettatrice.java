package it.unito.cassandraapiservice.model.impl.etichettatrice;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;

@Builder
@Data
@Table(value = "risultati_etichettatrice")
public class RisultatiEtichettatrice {


    @PrimaryKey
    PropertyKey key;

    @Column("timestamp")
    private String timestamp;

    @Column("codice_prodotto")
    private String codiceProdotto;

    @Column("oee")
    private float oee;

    @Column("oee_agg")
    private float oeeAgg;

    @Column("pezzi_prodotti")
    private int pezziProdotti;

    @Column("pezzi_prodotti_agg")
    private int pezziProdottiAgg;

    @PrimaryKeyClass
    @Builder
    public static class PropertyKey implements Serializable {
        @PrimaryKeyColumn(name = "data",
                type = PrimaryKeyType.PARTITIONED)
        private String data;

        @PrimaryKeyColumn(name = "tempo",
                type = PrimaryKeyType.CLUSTERED)
        private String tempo;

        @Override
        public String toString(){
            return data+"_"+tempo;
        }
    }
}
