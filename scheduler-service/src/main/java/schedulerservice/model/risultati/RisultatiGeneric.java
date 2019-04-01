package schedulerservice.model.risultati;

import lombok.Data;

@Data
public class RisultatiGeneric {


    private final String data;

    private final String tempo;

    private String timestamp;

    private String codiceProdotto;

    private float oee;

    private float oeeAgg;

    private int pezziProdotti;

    private int pezziProdottiAgg;

    public RisultatiGeneric(String data, String tempo) {
        this.data = data;
        this.tempo = tempo;
        timestamp = data+tempo;
    }
}
