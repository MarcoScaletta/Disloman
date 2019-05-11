package schedulerservice.model.smartshareobject;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class ListaCommesse {
    List<Commessa> lista_commesse;

    @Override
    public String toString() {
        return lista_commesse.toString();
    }
}
