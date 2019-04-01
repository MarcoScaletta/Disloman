package it.unito.cassandraapiservice.model.impl.risultati;


import org.springframework.data.cassandra.core.mapping.Table;


@Table(value = "risultati_astucciatrice")
public class RisultatiAstucciatrice extends RisultatiGeneric{

    public RisultatiAstucciatrice(String data, String tempo) {
        super(data, tempo);
    }
}
