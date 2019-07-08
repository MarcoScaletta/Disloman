package it.unito.cassandraapiservice.model.apiobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.unito.cassandraapiservice.model.impl.generic.RisultatiODLTurno;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RisultatiODLTurnoList {

    @JsonProperty("risultati_odl_turno_list")
    List<RisultatiODLTurno> risultatiODLTurnoList = new ArrayList<>();
}
