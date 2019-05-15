package schedulerservice.model.smartshareobject.odl.fasi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Fase {
    @JsonProperty("cod_fase")
    private String codFase;
    @JsonProperty("fase_attiva")
    private boolean active;
    @JsonProperty("lista_prodotti_lavorati")
    private List<ProdottoLavorato> listaProdottiLavorati;
}

