package it.unito.cassandraapiservice.model.impl.tappatrice;

import it.unito.cassandraapiservice.model.impl.generic.TempoCiclo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "tempo_ciclo_tappatrice")
public class TempoCicloTappatrice extends TempoCiclo {
}
