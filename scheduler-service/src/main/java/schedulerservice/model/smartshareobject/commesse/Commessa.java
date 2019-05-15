package schedulerservice.model.smartshareobject.commesse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Commessa {
    @JsonProperty("cod_commessa")
    String codCommessa;

    @Override
    public String toString() {
        return "Commessa{" +
                "codCommessa='" + codCommessa + '\'' +
                '}';
    }
}
