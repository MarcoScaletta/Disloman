package schedulerservice.model.smartshareobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ListaFasi {
    @JsonProperty("lista_fasi")
    public List<Fase> listaFasi;
}
