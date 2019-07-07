package it.unito.cassandraapiservice.model.impl.etichettatrice;

import it.unito.cassandraapiservice.model.impl.generic.TempoCiclo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "tempo_ciclo_etichettatrice")
public class TempoCicloEtichettatrice extends TempoCiclo {
}
