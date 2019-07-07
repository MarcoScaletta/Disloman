package it.unito.cassandraapiservice.model.impl.bilancia;

import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessaTurno;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "risultati_bilancia_commessa_turno")
public class RisultatiBilanciaCommessaTurno extends RisultatiCommessaTurno {
}
