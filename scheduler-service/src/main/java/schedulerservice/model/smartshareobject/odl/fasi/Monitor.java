package schedulerservice.model.smartshareobject.odl.fasi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Monitor {

    @JsonProperty("cod_monitor")
    private String codMonitor;
    @JsonProperty("timestamp_start_fase")
    private String timeStart;
    @JsonProperty("timestamp_stop_fase")
    private String timeStop;

    @Override
    public String toString() {
        return "Monitor{" +
                "codMonitor='" + codMonitor + '\'' +
                '}';
    }
}
