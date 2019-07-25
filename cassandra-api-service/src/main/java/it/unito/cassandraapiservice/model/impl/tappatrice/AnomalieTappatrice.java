package it.unito.cassandraapiservice.model.impl.tappatrice;

import it.unito.cassandraapiservice.model.impl.generic.Anomalie;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "anomalie_tappatrice")
public class AnomalieTappatrice extends Anomalie {
}
