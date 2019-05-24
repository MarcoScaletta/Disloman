package schedulerservice.model.records;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Record {

    private String time;

    private int pezziProdotti;

    private String codiceProdotto;

    @Override
    public String toString() {
        return "Record{" +
                "time='" + time + '\'' +
                ", pezziProdotti=" + pezziProdotti +
                ", codiceProdotto='" + codiceProdotto + '\'' +
                '}';
    }
}
