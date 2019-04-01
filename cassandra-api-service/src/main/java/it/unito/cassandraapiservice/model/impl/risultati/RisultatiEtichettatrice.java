package it.unito.cassandraapiservice.model.impl.risultati;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "risultati_etichettatrice")
public class RisultatiEtichettatrice extends RisultatiGeneric{
    public RisultatiEtichettatrice(String data, String tempo) {
        super(data, tempo);
    }
}
