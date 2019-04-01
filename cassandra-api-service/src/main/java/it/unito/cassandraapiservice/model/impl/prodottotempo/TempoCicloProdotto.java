package it.unito.cassandraapiservice.model.impl.prodottotempo;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

@Data
public class TempoCicloProdotto {

    @PrimaryKey("codice_prodotto")
    private String codiceProdotto;

    @Column("timestamp")
    private final String timestamp;

    @Column("contatore")
    private int contatore;

}
