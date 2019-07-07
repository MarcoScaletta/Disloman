package it.unito.cassandraapiservice.model.impl.smartshareobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
//@Table(value = "open_monitor")
public class OpenMonitor {

    @JsonProperty("cod_monitor")
    @PrimaryKey("cod_monitor")
    private String codMonitor;

    @JsonProperty("start_time")
    @Column("start_time")
    private String startTime;

}
