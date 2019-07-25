package it.unito.cassandraapiservice.model.impl.etichettatrice;

import it.unito.cassandraapiservice.model.impl.generic.Records;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "records_etichettatrice")
public class RecordsEtichettatrice extends Records {

    public RecordsEtichettatrice(){
        super();
    }
    public RecordsEtichettatrice(Records records){
        super(records);
    }
}

