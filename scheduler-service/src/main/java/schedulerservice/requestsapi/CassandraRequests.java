package schedulerservice.requestsapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.model.cassandraobjects.OpenMonitor;
import schedulerservice.model.records.RecordsList;
import schedulerservice.model.smartshareobject.Monitor;

import java.util.List;

@Slf4j
@Component
public class CassandraRequests {

    @Autowired
    private String cassandraAPIServiceAddress;

    @Autowired
    private RestTemplate restTemplate;


    // REST API CALL RECORD METHODS

    public void postRecords(RecordsList records, String machine){

        System.out.println("PRINTING TEST: " + cassandraAPIServiceAddress + "/" + machine);
        try {
            HttpEntity<?> entity = new HttpEntity<>(records);
            restTemplate.exchange(
                    cassandraAPIServiceAddress + "/records/" + machine + "/",
                    HttpMethod.POST,
                    entity,
                    RecordsList.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void postRecordsTappatrice(RecordsList records){
        postRecords(records, "tappatrice");
    }

    public void postRecordsEtichettatrice(RecordsList records){
        postRecords(records, "etichettatrice");
    }

    public void postRecordsIncartonatrice(RecordsList records){
        postRecords(records, "incartonatrice");
    }

    public void postRecordsBilancia(RecordsList records){
        postRecords(records, "bilancia");
    }


    // CLOSED MONITOR METHODS
    public void postMonitor(Monitor monitor){
        try{
            HttpEntity<?>  entity = new HttpEntity<>(monitor);
            log.info(cassandraAPIServiceAddress +"/closed-monitor/");
            restTemplate.postForObject(
                    cassandraAPIServiceAddress + "/closed-monitor/",
                    entity, Monitor.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Monitor> getClosedMonitors(){
        try{
            ResponseEntity<List<Monitor>> response = new RestTemplate().exchange(
                    cassandraAPIServiceAddress +"/closed-monitor",
                    HttpMethod.GET,
                    null,new ParameterizedTypeReference<List<Monitor>>(){});

            return response.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // OPEN MONITOR METHODS
    public void postOpenMonitor(OpenMonitor monitor){
        try{
            HttpEntity<?>  entity = new HttpEntity<>(monitor);
            log.info(cassandraAPIServiceAddress +"/open-monitor/");
            restTemplate.postForObject(
                    cassandraAPIServiceAddress + "/open-monitor/",
                    entity, Monitor.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public OpenMonitor getOpenMonitor(){
        try{
            ResponseEntity<OpenMonitor> response = new RestTemplate().exchange(
                    cassandraAPIServiceAddress +"/open-monitor/",
                    HttpMethod.GET,
                    null,OpenMonitor.class);
            return response.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteOpenMonitor(String codMonitor){
        try{
            ResponseEntity<Success> response = new RestTemplate().exchange(
                    cassandraAPIServiceAddress
                            +"/open-monitor/"+codMonitor+"/",
                    HttpMethod.DELETE,
                    null,Success.class);
            return response.getBody().isSuccess();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
