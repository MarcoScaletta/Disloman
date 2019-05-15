package it.unito.cassandraapiservice.model.impl.smartshareobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table(value = "inserted_closed_fase")
public class Fase {

    @JsonProperty("cod_fase")
    @PrimaryKey("cod_fase")
    private String codFase;

}
