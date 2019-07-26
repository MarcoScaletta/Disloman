package smarthingeapiservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Records {

    @JsonProperty("time")
    private String time;

    @JsonProperty("pezzi_prodotti")
    private int pezziProdotti;

}
