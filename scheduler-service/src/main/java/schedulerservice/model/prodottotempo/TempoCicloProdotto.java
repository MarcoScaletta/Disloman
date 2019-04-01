package schedulerservice.model.prodottotempo;

import lombok.Data;

@Data
public class TempoCicloProdotto {

    private String codiceProdotto;

    private final String timestamp;

    private int contatore;

}
