package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLTransientConnectionException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@Slf4j
public class RecordController {

    @Autowired
    @Qualifier("queryBilancia")
    QueryMachine queryBilancia;

    @Autowired
    @Qualifier("queryIncartonatrice")
    QueryMachine queryIncartonatrice;

    @Autowired
    @Qualifier("queryTappatrice")
    QueryMachine queryTappatrice;

    @Autowired
    @Qualifier("queryEtichettatrice")
    QueryMachine queryEtichettatrice;

    @Autowired
    QueryPLD queryPLD;

    private SimpleDateFormat completeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/records/macchina/{macchina}")
    public ResponseEntity<RecordsList> getRecords(@PathVariable String macchina, @RequestParam String start, @RequestParam String stop) {
        log.info("get records from " + macchina + "start: " + start + " stop: " + stop);
        QueryMachine queryMachine = getQueryMachine(macchina);
        if (queryMachine == null){
            log.info("Machine [" + macchina +"] not existing!");
            return ResponseEntity.status(404).body(null);
        }
        return getGenericRecords(queryMachine, start, stop);
    }

    @GetMapping("/activity")
    public ResponseEntity<Boolean> getRecords() {
        return ResponseEntity.ok(queryPLD.isActive());
    }

    public QueryMachine getQueryMachine(String macchina){

        switch (macchina){
            case "bilancia":return queryBilancia;
            case "etichettatrice": return queryEtichettatrice;
            case "tappatrice": return queryTappatrice;
            case "incartonatrice": return queryIncartonatrice;
            default: return null;
        }

    }

    private ResponseEntity<RecordsList> getGenericRecords(QueryMachine query, String start, String stop) {

        Timestamp startTime,stopTime;
        List<Records> records;
        RecordsList recordsList;
        try{
            startTime = new Timestamp(completeFormat.parse(start).getTime());
            stopTime  = new Timestamp(completeFormat.parse(stop).getTime());
            log.info("timestamps -> " +  "start: " + startTime + " stop: " + stopTime);
        }catch (ParseException p){
            log.info("Wrong date format");
            return ResponseEntity.status(400).body(null);
        }
        records = query.queryToMachine(startTime,stopTime);
        if(records == null)
            return ResponseEntity.status(500).body(null);
        recordsList = new RecordsList();
        recordsList.getRecordsList().addAll(records);

        return ResponseEntity.ok(recordsList);
    }
}
