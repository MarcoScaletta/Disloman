package it.unito.cassandraapiservice.model.impl.etichettatrice;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessaTurno;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "records_etichettatrice_commessa_turno")
public class RisultatiEtichettatriceCommessaTurno extends RisultatiCommessaTurno {
}

