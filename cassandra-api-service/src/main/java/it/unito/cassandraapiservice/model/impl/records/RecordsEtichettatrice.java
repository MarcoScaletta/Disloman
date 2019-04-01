package it.unito.cassandraapiservice.model.impl.records;

import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Time;

@Table(value = "records_etichettatrice")
public class RecordsEtichettatrice extends RecordsGeneric{
    public RecordsEtichettatrice(String time) {
        super(time);
    }
}
