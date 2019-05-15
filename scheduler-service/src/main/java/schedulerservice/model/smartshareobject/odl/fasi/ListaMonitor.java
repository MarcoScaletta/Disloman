package schedulerservice.model.smartshareobject.odl.fasi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ListaMonitor {
//    @JsonProperty("inserted_monitor")
    public List<Monitor> listaMonitor;
}
