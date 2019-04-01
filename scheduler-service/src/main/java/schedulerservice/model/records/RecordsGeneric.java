package schedulerservice.model.records;

import lombok.Data;

@Data
public class RecordsGeneric {

    private final String time;

    private int pezziProdotti;

    private String codiceProdotto;
}
