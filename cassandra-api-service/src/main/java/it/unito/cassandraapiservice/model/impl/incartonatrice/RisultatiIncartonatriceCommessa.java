package it.unito.cassandraapiservice.model.impl.incartonatrice;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "risultati_incartonatrice_commessa")
public class RisultatiIncartonatriceCommessa extends RisultatiCommessa {
}
