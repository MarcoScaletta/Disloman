package schedulerservice.model.smartshareobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProdottoLavorato {

    @JsonProperty("cod_commessa")
    private String codCommessa;

    @JsonProperty("cod_prodotto")
    private String codProdotto;

    @JsonProperty("nome_prodotto")
    private String nomeProdotto;
}
