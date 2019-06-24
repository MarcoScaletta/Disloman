package schedulerservice.requestsapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.model.records.Record;
import schedulerservice.model.smartshareobject.odl.fasi.Monitor;

import java.util.List;

@Slf4j
@Component
public class CassandraRequests {

    @Autowired
    private String tappatriceRecordsTableName;

    @Autowired
    private String incartonatriceRecordsTableName;

    @Autowired
    private String etichettatriceRecordsTableName;

    @Autowired
    private String bilanciaRecordsTableName;

    @Autowired
    private String cassandraAPIServiceAddress;

    @Autowired
    private RestTemplate restTemplate;


    // REST API CALL RECORD METHODS

    public void postRecords(List<Record> records, String table){
        HttpEntity<?>  entity = new HttpEntity<>(records);
        restTemplate.exchange(
                cassandraAPIServiceAddress + "/" + table,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<Record>>(){});
        System.out.println("PRINTING TEST: " + cassandraAPIServiceAddress + "/" + table);
    }

    public void postRecordsTappatrice(List<Record> records){
        System.out.println("postRecordsTappatrice");

        postRecords(records, tappatriceRecordsTableName);
    }

    public void postRecordsEtichettatrice(List<Record> records){
        System.out.println("postRecordsTappatrice");
        postRecords(records, etichettatriceRecordsTableName);
    }

    public void postRecordsIncartonatrice(List<Record> records){
        System.out.println("postRecordsTappatrice");
        postRecords(records, incartonatriceRecordsTableName);
    }

    public void postRecordsBilancia(List<Record> records){
        System.out.println("postRecordsTappatrice");
        postRecords(records, bilanciaRecordsTableName);
    }


    // MONITOR METHODS
    public void postMonitor(Monitor monitor){
        try{
            HttpEntity<?>  entity = new HttpEntity<>(monitor);
            log.info(cassandraAPIServiceAddress +"/monitor/");
            restTemplate.postForObject(
                    cassandraAPIServiceAddress + "/monitor/",
                    entity, Monitor.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Monitor> getSavedMonitors(){
        ResponseEntity<List<Monitor>> response = new RestTemplate().exchange(
                cassandraAPIServiceAddress +"/monitor",
                HttpMethod.GET,
                null,new ParameterizedTypeReference<List<Monitor>>(){});

        log.info(response.getBody().toString());
        return response.getBody();

    }

}
