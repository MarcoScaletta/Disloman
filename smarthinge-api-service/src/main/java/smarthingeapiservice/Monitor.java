package smarthingeapiservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Monitor {

    @JsonProperty("timestamp_start_fase")
    private String timeStart;
    @JsonProperty("timestamp_stop_fase")
    private String timeStop;

    @Override
    public String toString() {
        return "Monitor{" +
                "timeStart='" + timeStart + '\'' +
                ", timeStop='" + timeStop + '\'' +
                '}';
    }
}
