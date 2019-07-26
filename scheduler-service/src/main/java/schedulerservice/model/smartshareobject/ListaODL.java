package schedulerservice.model.smartshareobject;

import lombok.Getter;
import java.util.List;

@Getter
public class ListaODL {

    List<ODL> lista_odl;

    @Override
    public String toString() {
        return lista_odl.toString();
    }
}
