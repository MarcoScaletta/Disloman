package it.unito.cassandraapiservice.model.impl.smartshareobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
//@Table(value = "inserted_Closed_ODL")
public class ODL {

    @JsonProperty("cod_odl")
    @PrimaryKey("cod_odl")
    private String codODL;

}
