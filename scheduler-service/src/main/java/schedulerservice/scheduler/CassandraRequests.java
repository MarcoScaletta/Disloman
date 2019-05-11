package schedulerservice.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.model.records.RecordsTappatrice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CassandraRequests {

    @Autowired
    private String tappatriceRecordsTableName;

    @Autowired
    private String cassandraAPIServiceAddress;

    @Autowired
    private RestTemplate restTemplate;

    public void postTappatriceRecord(RecordsTappatrice record){
        HttpEntity<?>  entity = new HttpEntity<>(record);
        restTemplate.postForObject(
                cassandraAPIServiceAddress + tappatriceRecordsTableName,
                entity, RecordsTappatrice.class);
    }

    public void testPostTappatriceRecord(){

        String s = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        RecordsTappatrice r = new RecordsTappatrice(s,1,"1");
        postTappatriceRecord(r);
    }

}
