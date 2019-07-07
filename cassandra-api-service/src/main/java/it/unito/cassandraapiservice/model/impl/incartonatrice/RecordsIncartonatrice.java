package it.unito.cassandraapiservice.model.impl.incartonatrice;

import it.unito.cassandraapiservice.model.impl.generic.Records;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "records_incartonatrice")
public class RecordsIncartonatrice extends Records {
}
