package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    private SimpleDateFormat completeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    @GetMapping("/records_bilancia/monitor")
    public ResponseEntity<List<Record>> getBilanciaRecords(@RequestParam String start, @RequestParam String stop) {
        log.info("GET BILANCIA_RECORDS");
        return getGenericRecords(queryBilancia, start, stop);
    }

    @GetMapping("/records_incartonatrice/monitor")
    public ResponseEntity<List<Record>> getIncartonatriceRecords(@RequestParam String start, @RequestParam String stop) {
        log.info("GET INCARTONATRICE_RECORDS");
        return getGenericRecords(queryIncartonatrice, start, stop);
    }

    @GetMapping("/records_tappatrice/monitor")
    public ResponseEntity<List<Record>> getTappatriceRecords(@RequestParam String start, @RequestParam String stop) {
        log.info("GET TAPPATRICE_RECORDS");
        return getGenericRecords(queryTappatrice, start, stop);
    }

    @GetMapping("/records_etichettatrice/monitor")
    public ResponseEntity<List<Record>> getEtichettatriceRecords(@RequestParam String start, @RequestParam String stop) {
        log.info("GET ETICHETTATRICE_RECORDS");
        return getGenericRecords(queryEtichettatrice, start, stop);
    }

    private ResponseEntity<List<Record>> getGenericRecords(QueryMachine query, String start, String stop) {

        Timestamp startTime,stopTime;
        try{

            startTime = new Timestamp(completeFormat.parse(start).getTime());
            stopTime  = new Timestamp(completeFormat.parse(stop).getTime());
        }catch (ParseException p){
            log.info("Wrong date format");
            return ResponseEntity.status(400).body(null);
        }

        return ResponseEntity.ok(query.queryToMachine(startTime,stopTime));
    }
}
