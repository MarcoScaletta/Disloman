package it.unito.cassandraapiservice.model.apiobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.unito.cassandraapiservice.model.impl.generic.RisultatiCommessa;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RisultatiCommessaList {

    @JsonProperty("risultati_commessa_list")
    List<RisultatiCommessa> risultatiCommessaList = new ArrayList<>();
}
