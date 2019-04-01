package it.unito.cassandraapiservice.model.impl.risultati;

import com.datastax.driver.core.utils.UUIDs;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.sql.Time;
import java.util.UUID;

@Data
public class RisultatiGeneric {


    @PrimaryKeyColumn(name = "data",
            type = PrimaryKeyType.PARTITIONED)
    private final String data;

    @PrimaryKeyColumn(name = "tempo",
            type = PrimaryKeyType.CLUSTERED)
    private final String tempo;

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

    public RisultatiGeneric(String data, String tempo) {
        this.data = data;
        this.tempo = tempo;
        timestamp = data+tempo;
    }
}
