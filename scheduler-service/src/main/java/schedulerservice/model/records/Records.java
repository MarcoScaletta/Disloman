package schedulerservice.model.records;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Records {

    @JsonProperty("time")
    private String time;

    @JsonProperty("pezzi_prodotti")
    private int pezziProdotti;

    @JsonProperty("nome_prodotto")
    private String nomeProdotto;

    @JsonProperty("codice_prodotto")
    private String codiceProdotto;

    @JsonProperty("codice_commessa")
    private String codiceCommessa;

    @JsonProperty("turno")
    private String turno;

    @JsonProperty("codice_odl")
    private String codiceODL;

    @Override
    public String toString() {
        return "Records{" +
                "time='" + time + '\'' +
                ", pezziProdotti=" + pezziProdotti +
                ", codiceProdotto='" + codiceProdotto + '\'' +
                '}';
    }
}
