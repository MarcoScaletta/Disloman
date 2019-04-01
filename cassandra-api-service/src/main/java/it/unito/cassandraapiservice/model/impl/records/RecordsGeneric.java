package it.unito.cassandraapiservice.model.impl.records;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.sql.Time;

@Data
public class RecordsGeneric {

    @PrimaryKey("time")
    private final String time;

    @Column("pezzi_prodotti")
    private int pezziProdotti;

    @Column("codice_prodotto")
    private String codiceProdotto;
}
