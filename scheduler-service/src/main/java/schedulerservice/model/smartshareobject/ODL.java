package schedulerservice.model.smartshareobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ODL {

    @JsonProperty("cod_odl")
    private String codODL;
    @JsonProperty("num_fasi_prev")
    private long numFasiPrev;

    @Override
    public String toString() {
        return "ODL{" +
                "codiceODL='" + codODL + '\'' +
                ", numFasiPrev=" + numFasiPrev +
                '}';
    }
}
