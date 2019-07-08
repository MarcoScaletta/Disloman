package it.unito.cassandraapiservice.model.impl.realtime;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("nome_macchina")
    @PrimaryKey("nome_macchina")
    private String nomeMacchina;

    @JsonProperty("timestamp")
    @Column("timestamp")
    private String time;

    @JsonProperty("oee_dinamico")
    @Column("oee_dinamico")
    private String oeeDinamico;

    @JsonProperty("oee_aggregato")
    @Column("oee_aggregato")
    private String oeeAggregato;
}
