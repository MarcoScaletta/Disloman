package schedulerservice.model.cassandraobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClosedMonitor {

    @JsonProperty("cod_monitor")
    private String codMonitor;

}
