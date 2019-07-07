package it.unito.cassandraapiservice.model.impl.realtime;

import it.unito.cassandraapiservice.model.impl.generic.GenericRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(value = "real_time")
public class RealTime extends GenericRecord {

    @PrimaryKey("nome_macchina")
    private String nomeMacchina;

    @Column("timestamp")
    private String time;

    @Column("oee_dinamico")
    private String oeeDinamico;

    @Column("oee_aggregato")
    private String oeeAggregato;
}
