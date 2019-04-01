package it.unito.cassandraapiservice.model.impl.records;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Time;
@Data
@Table(value = "records_astucciatrice")
public class RecordsAstucciatrice extends RecordsGeneric{
    public RecordsAstucciatrice(String time) {
        super(time);
    }
}
