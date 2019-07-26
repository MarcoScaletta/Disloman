package schedulerservice.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecordsList {

    @JsonProperty("records_list")
    List<Records> recordsList = new ArrayList<>();
}
