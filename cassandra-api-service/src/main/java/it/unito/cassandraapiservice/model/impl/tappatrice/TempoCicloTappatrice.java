package it.unito.cassandraapiservice.model.impl.tappatrice;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Builder
@Data
@Table(value = "tempo_ciclo_tappatrice")
public class TempoCicloTappatrice {
    @PrimaryKey("codice_prodotto")
    private String codiceProdotto;

    @Column("timestamp")
    private final String timestamp;

    @Column("contatore")
    private int contatore;
}
