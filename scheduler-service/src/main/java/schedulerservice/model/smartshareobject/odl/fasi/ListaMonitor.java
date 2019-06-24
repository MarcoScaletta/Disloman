package schedulerservice.model.smartshareobject.odl.fasi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ListaMonitor {
    @JsonProperty("lista_monitor")
    public List<Monitor> listaMonitor;
}
