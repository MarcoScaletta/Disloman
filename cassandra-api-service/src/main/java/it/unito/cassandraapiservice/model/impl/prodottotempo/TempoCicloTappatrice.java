package it.unito.cassandraapiservice.model.impl.prodottotempo;


import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "tempo_ciclo_tappatrice")
public class TempoCicloTappatrice extends TempoCicloProdotto {
    public TempoCicloTappatrice(String timestamp) {
        super(timestamp);
    }
}
