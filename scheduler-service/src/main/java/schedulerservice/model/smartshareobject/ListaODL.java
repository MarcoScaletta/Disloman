package schedulerservice.model.smartshareobject;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class ListaODL {
    List<ODL> lista_odl;

    @Override
    public String toString() {
        return lista_odl.toString();
    }
}
