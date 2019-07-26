package schedulerservice.model.smartshareobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ListaMonitor {
    @JsonProperty("lista_monitor")
    private List<Monitor> listaMonitor;
}
