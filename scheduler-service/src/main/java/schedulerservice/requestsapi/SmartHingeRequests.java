package schedulerservice.requestsapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import schedulerservice.model.records.Record;
import schedulerservice.model.smartshareobject.Monitor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class SmartHingeRequests {

    @Autowired
    private String tappatriceRecordsTableName;

    @Autowired
    private String incartonatriceRecordsTableName;

    @Autowired
    private String etichettatriceRecordsTableName;

    @Autowired
    private String bilanciaRecordsTableName;

    @Autowired
    public String smartHingeAPIServiceAddress;

    public List<Record> getTappatriceRecord(Date start, Date stop){
        return getRecords(start, stop, tappatriceRecordsTableName);
    }

    public List<Record> getEtichettatriceRecord(Date start, Date stop){
        return getRecords(start, stop, etichettatriceRecordsTableName);
    }

    public List<Record> getIncartonatriceRecord(Date start, Date stop){
        return getRecords(start, stop, incartonatriceRecordsTableName);
    }

    public List<Record> getBilanciaRecord(Date start, Date stop){

        return getRecords(start, stop, bilanciaRecordsTableName);
    }


    public List<Record> getTappatriceRecord(Monitor monitor){
        return getRecords(monitor, tappatriceRecordsTableName);
    }

    public List<Record> getEtichettatriceRecord(Monitor monitor){
        return getRecords(monitor, etichettatriceRecordsTableName);
    }

    public List<Record> getIncartonatriceRecord(Monitor monitor){
        return getRecords(monitor, incartonatriceRecordsTableName);
    }

    public List<Record> getBilanciaRecord(Monitor monitor){
        return getRecords(monitor, bilanciaRecordsTableName);
    }

    public List<Record> getRecords(Monitor monitor,String urlMachine){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        ResponseEntity<List<Record>> response = new RestTemplate().exchange(
                smartHingeAPIServiceAddress + "/" + urlMachine +
                        "/monitor?start="+
                        sdf.format(new Timestamp(Long.parseLong(monitor.getTimeStart())))+
                        "&stop="+
                        sdf.format(new Timestamp(Long.parseLong(monitor.getTimeStop()))),
                HttpMethod.GET,
                null,new ParameterizedTypeReference<List<Record>>(){});

        if(response.getBody() != null)
            log.info("Dimensione dati ottenuti per [" + urlMachine + "] : " + response.getBody().size());
        return response.getBody();
    }

    public List<Record> getRecords(Date start, Date stop, String urlMachine){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ResponseEntity<List<Record>> response = new RestTemplate().exchange(
                smartHingeAPIServiceAddress + "/" + urlMachine +
                        "/monitor?start="+
                        sdf.format(start)+
                        "&stop="+
                        sdf.format(stop),
                HttpMethod.GET,
                null,new ParameterizedTypeReference<List<Record>>(){});
        if(response.getBody() != null)
            log.info("Dimensione dati ottenuti per [" + urlMachine + "] : " + response.getBody().size());
        return response.getBody();
    }



}
