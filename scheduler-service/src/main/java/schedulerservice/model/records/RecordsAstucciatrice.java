package schedulerservice.model.records;

import lombok.Data;

@Data
public class RecordsAstucciatrice extends RecordsGeneric{
    public RecordsAstucciatrice(String time) {
        super(time);
    }
}
