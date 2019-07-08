package it.unito.cassandraapiservice.model.apiobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.unito.cassandraapiservice.model.impl.generic.Records;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecordsList {

    @JsonProperty("records_list")
    List<Records> recordsList = new ArrayList<>();
}
