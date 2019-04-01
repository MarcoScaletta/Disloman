package it.unito.cassandraapiservice.model.impl.risultati;

import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "risultati_tappatrice")
public class RisultatiTappatrice extends RisultatiGeneric {
    public RisultatiTappatrice(String data, String tempo) {
        super(data, tempo);
    }
}
