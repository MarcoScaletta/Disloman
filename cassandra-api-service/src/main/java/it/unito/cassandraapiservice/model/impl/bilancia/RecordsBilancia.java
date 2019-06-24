package it.unito.cassandraapiservice.model.impl.bilancia;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Builder
@Data
@Table(value = "records_bilancia")
public class RecordsBilancia {

    @PrimaryKey("time")
    private final String time;

    @Column("pezzi_prodotti")
    private int pezziProdotti;

    @Column("codice_prodotto")
    private String codiceProdotto;
}

