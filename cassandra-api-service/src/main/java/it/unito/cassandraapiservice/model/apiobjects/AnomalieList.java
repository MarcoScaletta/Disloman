package it.unito.cassandraapiservice.model.apiobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.unito.cassandraapiservice.model.impl.generic.Anomalie;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AnomalieList {

    @JsonProperty("anomalie_list")
    List<Anomalie> recordsList = new ArrayList<>();
}
