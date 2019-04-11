package it.unito.cassandraapiservice.model.impl.etichettatrice;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Builder
@Data
@Table(value = "tempo_ciclo_etichettatrice")
public class TempoCicloEtichettatrice {
    @PrimaryKey("codice_prodotto")
    private String codiceProdotto;

    @Column("timestamp")
    private final String timestamp;

    @Column("contatore")
    private int contatore;
}
