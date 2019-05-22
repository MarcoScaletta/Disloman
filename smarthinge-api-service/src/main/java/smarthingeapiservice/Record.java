package smarthingeapiservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Record {

    private String time;

    private int pezziProdotti;

    private String codiceProdotto;
}
