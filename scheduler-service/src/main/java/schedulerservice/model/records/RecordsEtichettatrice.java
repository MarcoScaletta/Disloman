package schedulerservice.model.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RecordsEtichettatrice {

    private String time;

    private int pezziProdotti;

    private String codiceProdotto;

}

