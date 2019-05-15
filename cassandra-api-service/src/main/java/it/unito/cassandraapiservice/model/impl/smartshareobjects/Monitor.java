package it.unito.cassandraapiservice.model.impl.smartshareobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table(value = "inserted_monitor")
public class Monitor {

    @JsonProperty("cod_monitor")
    @PrimaryKey("cod_monitor")
    private String codMonitor;

}
