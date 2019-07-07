package it.unito.cassandraapiservice.model.impl.etichettatrice;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiODLTurno;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "risultati_etichettatrice_odl_turno")
public class RisultatiEtichettatriceODLTurno extends RisultatiODLTurno {
}
