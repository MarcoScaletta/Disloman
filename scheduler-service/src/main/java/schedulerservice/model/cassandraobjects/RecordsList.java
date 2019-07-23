package schedulerservice.model.cassandraobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import schedulerservice.model.records.Record;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecordsList {

    @JsonProperty("records_list")
    List<Record> recordsList = new ArrayList<>();
}
