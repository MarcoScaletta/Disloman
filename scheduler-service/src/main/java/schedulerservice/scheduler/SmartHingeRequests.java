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
import schedulerservice.model.smartshareobject.odl.ListaODL;
import schedulerservice.model.smartshareobject.odl.fasi.Monitor;

import java.util.List;

@Slf4j
@Component
public class SmartHingeRequests {

    @Autowired
    public String smartHingeAPIServiceAddress;


    public List<Record> getRecords(Monitor monitor){
        ResponseEntity<List<Record>> response = new RestTemplate().exchange(
                smartHingeAPIServiceAddress +
                        "/dummy_records_tappatrice?start="+
                        monitor.getTimeStart()+
                        "&stop="+
                        monitor.getTimeStop(),
                HttpMethod.GET,
                null,new ParameterizedTypeReference<List<Record>>(){});

        log.info(response.getBody().toString());
        return response.getBody();
    }



}
