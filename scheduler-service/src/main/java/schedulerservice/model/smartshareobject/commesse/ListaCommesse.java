package schedulerservice.model.smartshareobject.commesse;

import lombok.Getter;

import java.util.List;

@Getter
public class ListaCommesse {
    List<Commessa> lista_commesse;

    @Override
    public String toString() {
        return lista_commesse.toString();
    }
}
