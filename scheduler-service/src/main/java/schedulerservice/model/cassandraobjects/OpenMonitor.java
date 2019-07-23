package schedulerservice.model.cassandraobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenMonitor {

    @JsonProperty("cod_monitor")
    private String codMonitor;

    @JsonProperty("start_time")
    private String startTime;

}
