package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @GetMapping("/records_bilancia/monitor")
    public List<Record> getBilanciaRecords(@RequestParam String start, @RequestParam String stop)
            throws ParseException {
        log.info("GET BILANCIA_RECORDS: from <" + start +"> to <"+stop+">");
        return getGenericRecords(queryBilancia, start, stop);
    }

    @GetMapping("/records_incartonatrice/monitor")
    public List<Record> getIncartonatriceRecords(@RequestParam String start, @RequestParam String stop)
            throws ParseException {
        log.info("GET INCARTONATRICE_RECORDS: from <" + start +"> to <"+stop+">");
        return getGenericRecords(queryIncartonatrice, start, stop);
    }

    @GetMapping("/records_tappatrice/monitor")
    public List<Record> getTappatriceRecords(@RequestParam String start, @RequestParam String stop)
            throws ParseException {
        log.info("GET TAPPATRICE_RECORDS: from <" + start +"> to <"+stop+">");
        return getGenericRecords(queryTappatrice, start, stop);
    }

    @GetMapping("/records_etichettatrice/monitor")
    public List<Record> getEtichettatriceRecords(@RequestParam String start, @RequestParam String stop)
            throws ParseException {
        log.info("GET ETICHETTATRICE_RECORDS: from <" + start +"> to <"+stop+">");
        return getGenericRecords(queryEtichettatrice, start, stop);
    }

    private List<Record> getGenericRecords(QueryMachine query, String start, String stop)
            throws ParseException {
        Timestamp startTime,stopTime;

        startTime = new Timestamp(sdf1.parse(start).getTime());
        stopTime  = new Timestamp(sdf1.parse(stop).getTime());

        return query.queryToMachine(startTime,stopTime);

    }
}
