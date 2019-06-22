package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    QueryMachine queryTappatrice;

    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    @GetMapping("/records_tappatrice/monitor")
    public List<Record> getTappatriceRecords(@RequestParam String start, @RequestParam String stop) throws ParseException {
        log.info("GET TAPPATRICE_RECORDS: from <" + start +"> to <"+stop+">");
        Timestamp startTime,stopTime;

        startTime = new Timestamp(sdf1.parse(start).getTime());
        stopTime  = new Timestamp(sdf1.parse(stop).getTime());

        log.info("GET TAPPATRICE_RECORDS: from <" + startTime +"> to <"+stopTime+">");
        return queryTappatrice.queryToMachine(startTime,stopTime);
    }
}
