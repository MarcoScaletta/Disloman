package it.unito.cassandraapiservice.model.impl.generic;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class Records extends GenericRecord{

    @PrimaryKey("timestamp")
    private String time;

    @Column("pezzi_prodotti")
    private int pezziProdotti;

}
