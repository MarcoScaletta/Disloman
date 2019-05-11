package schedulerservice.model.smartshareobject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Commessa {
    String cod_commessa;

    @Override
    public String toString() {
        return "Commessa{" +
                "cod_commessa='" + cod_commessa + '\'' +
                '}';
    }
}
