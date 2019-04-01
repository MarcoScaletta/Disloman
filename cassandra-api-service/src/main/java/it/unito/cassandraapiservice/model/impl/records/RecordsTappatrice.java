package it.unito.cassandraapiservice.model.impl.records;

import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Time;

@Table(value = "records_tappatrice")
public class RecordsTappatrice extends RecordsGeneric{
    public RecordsTappatrice(String time) {
        super(time);
    }
}
