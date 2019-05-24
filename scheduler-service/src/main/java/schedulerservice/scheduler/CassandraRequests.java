package schedulerservice.scheduler;

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
    private String cassandraAPIServiceAddress;

    @Autowired
    private RestTemplate restTemplate;

    public void postTappatriceRecord(Record record){
        HttpEntity<?>  entity = new HttpEntity<>(record);
        restTemplate.postForObject(
                cassandraAPIServiceAddress + tappatriceRecordsTableName,
                entity, Record.class);
    }

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

//    public void postFase(ODL odl){
//        HttpEntity<?>  entity = new HttpEntity<>(odl);
//        log.info(cassandraAPIServiceAddress +"/odl/");
//        restTemplate.postForObject(
//                cassandraAPIServiceAddress + "/odl/",
//                entity, ODL.class);
//    }
//
//    public void postODL(ODL odl){
//        HttpEntity<?>  entity = new HttpEntity<>(odl);
//        log.info(cassandraAPIServiceAddress +"/odl/");
//        restTemplate.postForObject(
//                cassandraAPIServiceAddress + "/odl/",
//                entity, ODL.class);
//    }
//
//    public void testPostMonitor(){
//
//        String s = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
//        Monitor m = new Monitor(s,"0","0");
//        postMonitor(m);
//    }


    public List<Monitor> getSavedMonitors(){
    ResponseEntity<List<Monitor>> response = new RestTemplate().exchange(
            cassandraAPIServiceAddress +"/monitor",
            HttpMethod.GET,
            null,new ParameterizedTypeReference<List<Monitor>>(){});

        log.info(response.getBody().toString());
        return response.getBody();

    }

}
