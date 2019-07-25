package it.unito.cassandraapiservice.model.impl.incartonatrice;

import it.unito.cassandraapiservice.model.impl.generic.Anomalie;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "anomalie_incartonatrice")
public class AnomalieIncartonatrice extends Anomalie {
}
