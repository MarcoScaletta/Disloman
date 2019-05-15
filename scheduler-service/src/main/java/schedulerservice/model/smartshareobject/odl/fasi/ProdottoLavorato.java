package schedulerservice.model.smartshareobject.odl.fasi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProdottoLavorato {
    @JsonProperty("cod_prodotto")
    private String codProdotto;
}
