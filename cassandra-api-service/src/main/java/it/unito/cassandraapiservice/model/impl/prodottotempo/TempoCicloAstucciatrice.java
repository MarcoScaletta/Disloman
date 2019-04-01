package it.unito.cassandraapiservice.model.impl.prodottotempo;


import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "tempo_ciclo_astucciatrice")
public class TempoCicloAstucciatrice extends TempoCicloProdotto {
    public TempoCicloAstucciatrice(String timestamp) {
        super(timestamp);
    }
}
