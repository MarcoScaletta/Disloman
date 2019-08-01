package it.unito.cassandraapiservice.model.apiobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.unito.cassandraapiservice.model.impl.generic.TempoCiclo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TempoCicloList {

    @JsonProperty("tempo_ciclo_list")
    List<TempoCiclo> recordsList = new ArrayList<>();
}
