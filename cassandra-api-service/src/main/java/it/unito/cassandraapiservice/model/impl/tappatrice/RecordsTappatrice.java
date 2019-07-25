package it.unito.cassandraapiservice.model.impl.tappatrice;

import it.unito.cassandraapiservice.model.impl.generic.Records;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "records_tappatrice")
public class RecordsTappatrice extends Records {

    public RecordsTappatrice(){
        super();
    }
    public RecordsTappatrice(Records records){
        super(records);
    }
}
