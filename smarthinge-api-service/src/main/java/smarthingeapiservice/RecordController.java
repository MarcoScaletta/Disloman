package smarthingeapiservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class RecordController {

    @Autowired
    QueryTappatrice queryTappatrice;

//    @GetMapping("/records_tappatrice/monitor")
//    public List<Record> getRecords(@RequestBody Monitor monitor){
//        log.info("info");
//        return queryTappatrice.getRecordsFromMonitor(monitor);
//    }

    @GetMapping("/dummy_records_tappatrice")
    public List<Record> getDummyRecords(@RequestBody Monitor monitor){
        log.info("REQUEST");
        return queryTappatrice.dummyRecordsFromMonitor(monitor);
    }
}
