package it.unito.cassandraapiservice.model.apiobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.unito.cassandraapiservice.model.impl.generic.Risultati;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RisultatiList {

    @JsonProperty("risultati_list")
    List<Risultati> risultatiList = new ArrayList<>();

}
