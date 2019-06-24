package schedulerservice.model.smartshareobject.odl.fasi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Monitor {

    @JsonProperty("cod_monitor")
    private String codMonitor;
    @JsonProperty("timestamp_start_fase")
    private String timeStart;
    @JsonProperty("timestamp_stop_fase")
    private String timeStop;
    @JsonProperty("cod_odl")
    private String codODL;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor monitor = (Monitor) o;
        return Objects.equals(codMonitor, monitor.codMonitor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codMonitor);
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "codMonitor='" + codMonitor + '\'' +
                '}';
    }
}
