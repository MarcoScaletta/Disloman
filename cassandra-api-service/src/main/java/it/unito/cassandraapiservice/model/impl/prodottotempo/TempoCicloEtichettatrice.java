package it.unito.cassandraapiservice.model.impl.prodottotempo;


import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "tempo_ciclo_etichettatrice")
public class TempoCicloEtichettatrice extends TempoCicloProdotto {
    public TempoCicloEtichettatrice(String timestamp) {
        super(timestamp);
    }
}
