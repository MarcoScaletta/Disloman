package schedulerservice.model.records;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import schedulerservice.Parser;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RecordsTappatrice {

    public static String id = "78";

    public static Parser parser;

    static {
        try {
            parser = new Parser(
                    new String[]{"DI12","DI13"},
                    new String[]{"DI12:(.*?),", "DI13:(.*?),"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String time;

    private int pezziProdotti;

    private String codiceProdotto;

}
