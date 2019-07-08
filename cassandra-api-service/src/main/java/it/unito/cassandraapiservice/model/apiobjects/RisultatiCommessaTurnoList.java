package it.unito.cassandraapiservice.model.apiobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessaTurno;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RisultatiCommessaTurnoList {

    @JsonProperty("risultati_commessa_turno_list")
    List<RisultatiCommessaTurno> risultatiCommessaTurnoList = new ArrayList<>();
}
